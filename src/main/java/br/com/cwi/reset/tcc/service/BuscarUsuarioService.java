package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.exceptions.UsuarioNaoEncontradoException;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario buscarUsuario(final Long id) {

        Usuario usuarios = repository.findById(id).orElse(null);

        if (usuarios == null) {
            throw new UsuarioNaoEncontradoException();
        }

        return usuarios;
    }
}
