package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.exceptions.ProdutoNaoEncontradoException;
import br.com.cwi.reset.tcc.exceptions.UsuarioNaoEncontradoException;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto buscarProduto(final Long id) {

        Produto produto = repository.findById(id).orElse(null);

        if (produto == null) {
            throw new ProdutoNaoEncontradoException();
        }

        return produto;
    }
}
