package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.exceptions.EstabelecimentoNaoEncontradoException;
import br.com.cwi.reset.tcc.mapper.ProdutoResponseMapper;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import br.com.cwi.reset.tcc.request.CriarProdutoRequest;
import br.com.cwi.reset.tcc.response.ProdutoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastrarProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private BuscarEstabelecimentoService buscarEstabelecimentoService;

    @Autowired
    private ProdutoResponseMapper produtoResponseMapper;

    @Transactional
    public ProdutoResponse salvar(final CriarProdutoRequest request) {
        Produto produtosalvar = new Produto();

        Long idEstabelecimento = request.getIdEstabelecimento();
        Estabelecimento estabelecimento = buscarEstabelecimentoService.buscar(idEstabelecimento);

        if (idEstabelecimento == null) {
            throw new EstabelecimentoNaoEncontradoException();
        }

        if (request.getTempoPreparo() == null || request.getTempoPreparo() == 0) {
            produtosalvar.setTempoPreparo(30);
        } else {
            produtosalvar.setTempoPreparo(request.getTempoPreparo());
        }

        produtosalvar.setEstabelecimento(estabelecimento);
        produtosalvar.setTitulo(request.getTitulo());
        produtosalvar.setDescricao(request.getDescricao());
        produtosalvar.setCategoria(request.getCategoria());
        produtosalvar.setUrlFoto(request.getUrlFoto());
        produtosalvar.setValor(request.getValor());

        repository.save(produtosalvar);

        return produtoResponseMapper.mapear(produtosalvar);
    }
}
