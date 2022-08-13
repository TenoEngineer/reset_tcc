package br.com.cwi.reset.tcc.controller;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import br.com.cwi.reset.tcc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoRepository repository;

    @Autowired
    private CadastrarEstabelecimentoService salvarEstabelecimento;

    @Autowired
    private BuscarEstabelecimentoService buscarEstabelecimentoService;

    @Autowired
    private AdicionarEnderecoEstabelecimentoService adicionarEnderecoService;

    @Autowired
    private RemoverEnderecoEstabelecimentoService excluirEnderecoService;

    @Autowired
    private ListarEstabelecimentoService listarEstabelecimentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estabelecimento cadastrarEstabelecimento(@RequestBody @Valid Estabelecimento estabelecimento){
        return salvarEstabelecimento.salvar(estabelecimento);
    }

    @GetMapping
    public Page<Estabelecimento> listarEstabelecimento(Pageable pageable){
        return listarEstabelecimentoService.listarEstabelecimento(pageable);
    }

    @GetMapping("/{id}")
    public Estabelecimento buscarEstabelecimento(@PathVariable("id") Long id){
        return buscarEstabelecimentoService.buscar(id);
    }

    @PostMapping("/{id}/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarEndereco(Estabelecimento estabelecimento, @RequestBody Endereco endereco){
        adicionarEnderecoService.adicionar(estabelecimento, endereco);
    }

    @DeleteMapping("/{id}/enderecos/{idEndereco}")
    public void excluirEndereco(@PathVariable("id") Long id, @PathVariable("idEndereco") Long idEndereco){
        excluirEnderecoService.remover(id, idEndereco);
    }


}
