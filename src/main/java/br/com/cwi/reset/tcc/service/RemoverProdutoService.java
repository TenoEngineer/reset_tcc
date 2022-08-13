package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.exceptions.EnderecoNaoCadastradoException;
import br.com.cwi.reset.tcc.exceptions.IdNuloException;
import br.com.cwi.reset.tcc.exceptions.ProdutoNaoEncontradoException;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoverProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public void excluir(final Long id) {

        if (id == null || id == 0) {
            throw new IdNuloException();
        }

        Produto produto = repository.findById(id).orElse(null);

        if (produto == null) {
            throw new ProdutoNaoEncontradoException();
        }

        repository.delete(produto);

    }

}
