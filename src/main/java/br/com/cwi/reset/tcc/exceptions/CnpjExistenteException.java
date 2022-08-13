package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CnpjExistenteException extends RuntimeException {

    public CnpjExistenteException() {
        super("CNPJ já cadastrado");
    }

}
