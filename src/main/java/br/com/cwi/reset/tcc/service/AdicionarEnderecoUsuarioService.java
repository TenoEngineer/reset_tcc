package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarEnderecoUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public Usuario adicionarEndereco(final Usuario user, final Endereco endereco) {
        Usuario usuario = buscarUsuarioService.buscarUsuario(user.getId());
        usuario.getEnderecos().add(endereco);
        return repository.save(usuario);
    }

}
