package br.com.cwi.reset.tcc.controller;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.request.CriarPedidoRequest;
import br.com.cwi.reset.tcc.response.BuscarPedidoResponse;
import br.com.cwi.reset.tcc.response.FazerPedidoResponse;
import br.com.cwi.reset.tcc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private CadastrarPedidoService cadastrarPedidoService;

    @Autowired
    private BuscarPedidoService buscarPedidoService;

    @Autowired
    private EntregarPedidoService entregarPedidoService;

    @Autowired
    private FinalizarPedidoService finalizarPedidoService;

    @Autowired
    private CancelarPedidoService cancelarPedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FazerPedidoResponse fazerPedido(@RequestBody @Valid CriarPedidoRequest request) {
        return cadastrarPedidoService.salvarPedido(request);
    }

    @GetMapping("/{id}")
    public BuscarPedidoResponse buscarPedido(@PathVariable("id") @Valid final Long id) {
        return buscarPedidoService.buscarPedido(id);
    }

    @PutMapping("/{id}/entregar")
    public Entregador entregarPedido(@PathVariable("id") final Long id) {
        return entregarPedidoService.entregarPedido(id);
    }

    @PutMapping("/{id}/finalizar")
    public void finalizarPedido(@PathVariable("id") final Long id) {
        finalizarPedidoService.finalizarPedido(id);
    }

    @DeleteMapping("/{id}")
    public void cancelarPedido(@PathVariable("id") final Long id) {
        cancelarPedidoService.cancelarPedido(id);
    }
}
