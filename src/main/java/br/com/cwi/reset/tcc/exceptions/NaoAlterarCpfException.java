package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NaoAlterarCpfException extends RuntimeException {
    public NaoAlterarCpfException() {
        super("Não é possível alterar CPF");
    }
}
