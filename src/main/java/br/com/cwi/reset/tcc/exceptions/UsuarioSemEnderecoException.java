package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioSemEnderecoException extends RuntimeException {
    public UsuarioSemEnderecoException() {
        super("Usuário sem endereço cadastrado");
    }
}
