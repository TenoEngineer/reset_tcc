package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.swing.plaf.SeparatorUI;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LimiteDeItensException extends RuntimeException {
    public LimiteDeItensException() {
        super("Excesso de itens por pedido");
    }
}
