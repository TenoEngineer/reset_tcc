package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.exceptions.EnderecoNaoCadastradoException;
import br.com.cwi.reset.tcc.exceptions.IdNuloException;
import br.com.cwi.reset.tcc.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoverEnderecoUsuarioService {


    @Autowired
    private BuscarUsuarioService buscarUsuario;

    @Autowired
    private EnderecoRepository repository;

    public void removerEndereco(final Long id, final Long idEndereco) {

        if (idEndereco == null || id == null) {
            throw new IdNuloException();
        }
        Usuario usuario = buscarUsuario.buscarUsuario(id);
        Endereco endereco = repository.findById(idEndereco).orElse(null);

        if (endereco == null) {
            throw new EnderecoNaoCadastradoException();
        }

        if (usuario.getEnderecos().contains(endereco)) {
            usuario.getEnderecos().remove(endereco);
            repository.delete(endereco);
        } else {
            throw new EnderecoNaoCadastradoException();
        }

    }

}
