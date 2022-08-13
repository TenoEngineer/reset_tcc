package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.*;
import br.com.cwi.reset.tcc.exceptions.*;
import br.com.cwi.reset.tcc.mapper.ItemPedidoEntityMapper;
import br.com.cwi.reset.tcc.mapper.PedidoResponseMapper;
import br.com.cwi.reset.tcc.repository.*;
import br.com.cwi.reset.tcc.request.CriarPedidoRequest;
import br.com.cwi.reset.tcc.request.ItemPedidoRequest;
import br.com.cwi.reset.tcc.response.FazerPedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

@Service
public class CadastrarPedidoService {

    @Autowired
    private ItemPedidoEntityMapper itemPedidoEntityMapper;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BuscarEstabelecimentoService buscarEstabelecimentoService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private PedidoResponseMapper pedidoResponseMapper;

    @Autowired
    private ItemPedidoRequest itemPedidoRequest;

    @Autowired
    private BuscarProdutoService buscarProdutoService;

    static final int LIMITE_ITENS = 5;

    public FazerPedidoResponse salvarPedido(final CriarPedidoRequest request) {

        Pedido pedidoSalvar = new Pedido();

        validarPedido(request);

        Estabelecimento estabelecimento = buscarEstabelecimentoService.buscar(request.getIdEstabelecimento());
        Usuario usuario = buscarUsuarioService.buscarUsuario(request.getIdUsuarioSolicitante());

        Endereco enderecoEntrega = enderecoRepository.findById(request.getIdEnderecoEntrega()).orElse(null);
        if (usuario.getEnderecos().contains(enderecoEntrega)) {
            pedidoSalvar.setEnderecoEntrega(enderecoEntrega);
        } else {
            throw new EnderecoNaoCadastradoException();
        }

        pedidoSalvar.setEstabelecimento(estabelecimento);
        pedidoSalvar.setSolicitante(usuario);
        pedidoSalvar.setHorarioSolicitacao(LocalDateTime.now());
        pedidoSalvar.setItensPedido(itemPedidoEntityMapper.mapear(request.getItens()));
        pedidoSalvar.setFormaPagamento(request.getFormaPagamento());

        int tempoPreparoTotal = 0;
        BigDecimal valorTotal = BigDecimal.ZERO;
        validarQuantidade(request);
        for (ItemPedido itemPedido : pedidoSalvar.getItensPedido()) {

            Produto produto = itemPedido.getProduto();
            Integer quantidade = itemPedido.getQuantidade();
            BigDecimal valor = new BigDecimal(String.valueOf(produto.getValor())).multiply(new BigDecimal(String.valueOf(quantidade)));
            valorTotal = valorTotal.add(valor);
            pedidoSalvar.setValorTotal(valorTotal);
            int tempoPreparo = produto.getTempoPreparo() * quantidade;
            tempoPreparoTotal += tempoPreparo;
            pedidoSalvar.setHorarioEntrega(pedidoSalvar.getHorarioSolicitacao().plusMinutes(tempoPreparoTotal));

        }

        pedidoSalvar.setStatusPedido(StatusPedido.EM_PREPARO);

        pedidoRepository.save(pedidoSalvar);

        return pedidoResponseMapper.mapear(pedidoSalvar);
    }

    private void validarPedido(final CriarPedidoRequest request) {

        Estabelecimento estabelecimento = buscarEstabelecimentoService.buscar(request.getIdEstabelecimento());
        Usuario usuario = buscarUsuarioService.buscarUsuario(request.getIdUsuarioSolicitante());

        //Caso nenhum estabelecimento seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
        if (!(estabelecimentoRepository.existsById(request.getIdEstabelecimento()))) {
            throw new EstabelecimentoNaoEncontradoException();
        }


        //Caso nenhum usuario seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
        if (!(usuarioRepository.existsById(request.getIdUsuarioSolicitante()))) {
            throw new UsuarioNaoEncontradoException();
        }
//
        //Caso nenhum endereço seja localizado, deve lançar uma exceção com o status 404 e uma mensagem informando o problema
        if (!(request.getIdEnderecoEntrega() == null)) {
            if (!(enderecoRepository.existsById(request.getIdEnderecoEntrega()))) {
                throw new EnderecoNaoCadastradoException();
            }
        } else {
            throw new EnderecoNullException();
        }

        //Não deve ser possível fazer um pedido usando uma forma de pagamento não aceita pelo estabelecimento
        if (!(estabelecimento.getFormasPagamento().contains(request.getFormaPagamento()))) {
            throw new FormaDePagamentoException();
        }

        //Caso o usuário não possua endereços, deve lançar uma exception que retorne o status 400 e uma mensagem informando o problema
        if (usuario.getEnderecos().size() == 0 || usuario.getEnderecos() == null) {
            throw new UsuarioSemEnderecoException();
        }

        //Não deve ser possível fazer um pedido em um estabelecimento que não esteja em funcionamento no momento do pedido
        //Caso o estabelecimento esteja fechado, deve lançar uma exception que retorne o status 400 e uma mensagem informando o problema
        validacaoFinalHorario(estabelecimento);

        //Não deve ser possível fazer um pedido com itens que não sejam do estabelecimento informado
        //Caso tenha algum item que não seja do estabelecimento informado, deve lançar uma exception que retorne o status 400 e uma mensagem informando o problema
        for (ItemPedidoRequest item : request.getItens()) {
            Long idProduto = item.getIdProduto();
            Produto produtoProcurado = buscarProdutoService.buscarProduto(idProduto);
            if (!(produtoProcurado.getEstabelecimento().equals(estabelecimento))) {
                throw new ItemNaoCadastradoEnderecoExcetion();
            }
        }
    }

    public boolean validarHorarioEDia(Estabelecimento estabelecimento){
        LocalDateTime horarioSolicitado = LocalDateTime.now();
        for (HorarioFuncionamento horarioFuncionamento : estabelecimento.getHorariosFuncionamento()) {
            if (horarioSolicitado.getDayOfWeek() == horarioFuncionamento.getDiaSemana()) {
                if (horarioFuncionamento.getHorarioAbertura().isBefore(LocalTime.from(horarioSolicitado)) ||
                        horarioFuncionamento.getHorarioFechamento().isAfter(LocalTime.from(horarioSolicitado))) {
                    return true;
                }
            }
        } return false;
    }

    public void validacaoFinalHorario(Estabelecimento estabelecimento){
        if(!validarHorarioEDia(estabelecimento)){
            throw new EstabelecimentoFechadoException();
        }
    }

    public void validarQuantidade(CriarPedidoRequest request){
        for (ItemPedidoRequest itemPedido : request.getItens()) {
            if(itemPedido.getQuantidade() > LIMITE_ITENS){
                throw new LimiteDeItensException();
            }
        }
    }
}



