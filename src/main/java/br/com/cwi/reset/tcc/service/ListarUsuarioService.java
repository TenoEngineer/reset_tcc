package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Page<Usuario> listarUsuario(Pageable pageable) {

        return repository.findAllByOrderByNome(pageable);

    }
}
