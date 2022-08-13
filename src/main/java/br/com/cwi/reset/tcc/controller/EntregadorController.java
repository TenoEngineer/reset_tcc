package br.com.cwi.reset.tcc.controller;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.service.CadastrarEntregadorService;
import br.com.cwi.reset.tcc.service.ListarEntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    @Autowired
    private CadastrarEntregadorService cadastrarEntregadorService;

    @Autowired
    private ListarEntregadorService listarEntregadorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entregador cadastrarEntregador(@RequestBody @Valid Entregador entregador){
        return cadastrarEntregadorService.salvar(entregador);
    }

    @GetMapping
    public Page<Entregador> listarEntregador(Pageable pageable){
        return listarEntregadorService.listarEntregador(pageable);
    }
}
