package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.exceptions.EmailExistenteException;
import br.com.cwi.reset.tcc.exceptions.NaoAlterarCpfException;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BuscarUsuarioService buscarUsuario;

    public Usuario alterarUsuario(final Long id, final Usuario user) {
        final Usuario usuario = buscarUsuario.buscarUsuario(id);
        alterarCpf(user.getCpf());
        validarEmail(user);
        usuario.setNome(user.getNome());
        usuario.setEmail(user.getEmail());
        usuario.setSenha(user.getSenha());
        if(user.getEnderecos() != null){
            usuario.getEnderecos().addAll(user.getEnderecos());
        }
        return repository.save(usuario);
    }

    private void alterarCpf(final String cpf) {
        if (cpf != null) {
            throw new NaoAlterarCpfException();
        }
    }

    private void validarEmail(Usuario usuario) {
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new EmailExistenteException();
        }
    }
}
