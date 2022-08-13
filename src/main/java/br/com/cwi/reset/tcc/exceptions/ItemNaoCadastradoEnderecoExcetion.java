package br.com.cwi.reset.tcc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemNaoCadastradoEnderecoExcetion extends RuntimeException {
    public ItemNaoCadastradoEnderecoExcetion() {
        super("Item não cadastrado no estabelecimento informnado");
    }
}
