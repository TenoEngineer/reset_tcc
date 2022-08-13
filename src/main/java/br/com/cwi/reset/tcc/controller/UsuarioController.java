package br.com.cwi.reset.tcc.controller;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import br.com.cwi.reset.tcc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AdicionarEnderecoUsuarioService adicionarEnderecoService;

    @Autowired
    private RemoverEnderecoUsuarioService removerEnderecoService;

    @Autowired
    private AlterarUsuarioService alterarUsuarioService;

    @Autowired
    private ListarUsuarioService listarUsuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
        return cadastrarUsuarioService.salvar(usuario);
    }

    @GetMapping
    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return listarUsuarioService.listarUsuario(pageable);
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable("id") final Long id) {
        return buscarUsuarioService.buscarUsuario(id);
    }

    @PostMapping("/{id}/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarEndereco(@RequestBody @Valid Endereco endereco, Usuario usuario) {
        adicionarEnderecoService.adicionarEndereco(usuario, endereco);
    }

    @DeleteMapping("/{id}/enderecos/{idEndereco}")
    public void removerEndereco(@PathVariable("id") Long id, @PathVariable("idEndereco") Long idEndereco) {
        removerEnderecoService.removerEndereco(id, idEndereco);
    }

    @PutMapping("/{id}")
    public Usuario alterarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        return alterarUsuarioService.alterarUsuario(id, usuario);
    }
}
