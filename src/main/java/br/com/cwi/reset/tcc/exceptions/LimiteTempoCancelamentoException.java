package br.com.cwi.reset.tcc.exceptions;

public class LimiteTempoCancelamentoException extends RuntimeException {
    public LimiteTempoCancelamentoException() {
        super("Limite de tempo máximo de 10 minutos para o cancelamento do pedido");
    }
}
