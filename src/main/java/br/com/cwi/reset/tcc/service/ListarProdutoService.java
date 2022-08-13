package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Page<Produto> listarProduto(Pageable pageable) {

        return repository.findAllByOrderByTitulo(pageable);

    }
}
