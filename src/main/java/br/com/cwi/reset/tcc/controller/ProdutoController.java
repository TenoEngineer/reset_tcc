package br.com.cwi.reset.tcc.controller;

import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import br.com.cwi.reset.tcc.request.CriarProdutoRequest;
import br.com.cwi.reset.tcc.response.ProdutoResponse;
import br.com.cwi.reset.tcc.service.CadastrarProdutoService;
import br.com.cwi.reset.tcc.service.ListarProdutoService;
import br.com.cwi.reset.tcc.service.RemoverProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CadastrarProdutoService cadastrarProdutoService;

    @Autowired
    private ListarProdutoService listarProdutoService;

    @Autowired
    private RemoverProdutoService removerProdutoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse cadastrarProduto(@RequestBody @Valid CriarProdutoRequest request){
        return cadastrarProdutoService.salvar(request);
    }

    @GetMapping
    public Page<Produto> listarProduto(Pageable pageable){
        return listarProdutoService.listarProduto(pageable);
    }

    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable("id") Long id){
        removerProdutoService.excluir(id);
    }


}
