package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HorarioException extends RuntimeException {
    public HorarioException() {
        super("Horário com informações inviáveis");
    }
}
