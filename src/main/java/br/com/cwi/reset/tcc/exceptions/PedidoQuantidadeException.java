package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoQuantidadeException extends RuntimeException {
    public PedidoQuantidadeException() {
        super("Pedido solicitado acima de 5 unidades");
    }
}
