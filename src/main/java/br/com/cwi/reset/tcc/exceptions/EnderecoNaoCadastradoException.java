package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnderecoNaoCadastradoException extends RuntimeException{
    public EnderecoNaoCadastradoException() {
        super("ID de endereço não cadastrado.");
    }
}
