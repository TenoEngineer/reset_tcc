package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormaDePagamentoException extends RuntimeException {
    public FormaDePagamentoException() {
        super("Forma de pagamento não aceita pelo estabelecimento");
    }
}
