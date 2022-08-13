package br.com.cwi.reset.tcc.exceptions;

public class IdNuloException extends RuntimeException{
    public IdNuloException() {
        super("ID nulo. Insira algum valor no ID");
    }
}
