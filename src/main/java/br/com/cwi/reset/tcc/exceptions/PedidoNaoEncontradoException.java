package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException() {
        super("ID de pedido não encontrado");
    }
}
