package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.exceptions.CpfInvalidoException;
import br.com.cwi.reset.tcc.exceptions.EmailExistenteException;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(final Usuario usuario) {
        usuario.setId(null);
        validarUsuario(usuario);
        return repository.save(usuario);
    }

    private void validarUsuario(final Usuario usuario){
        if(repository.existsByEmail(usuario.getEmail())){
            throw new EmailExistenteException();
        }

        if(repository.existsByCpf(usuario.getCpf())){
            throw new CpfInvalidoException();
        }
    }
}
