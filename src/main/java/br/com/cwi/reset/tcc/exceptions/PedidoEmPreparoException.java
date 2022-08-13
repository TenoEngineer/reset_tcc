package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoEmPreparoException extends RuntimeException {
    public PedidoEmPreparoException() {
        super("Pedido não se encontra em preparo");
    }
}
