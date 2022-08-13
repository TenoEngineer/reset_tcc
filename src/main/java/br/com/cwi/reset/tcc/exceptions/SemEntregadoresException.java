package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SemEntregadoresException extends RuntimeException {
    public SemEntregadoresException() {
        super("Não há Entregadores disponíveis no momento. Por favor, tentar novamente mais tarde.");
    }
}
