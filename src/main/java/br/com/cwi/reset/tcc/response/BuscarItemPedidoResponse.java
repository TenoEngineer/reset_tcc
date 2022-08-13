package br.com.cwi.reset.tcc.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BuscarItemPedidoResponse {

    private String tituloPedido;
    private Integer quantidade;
}
