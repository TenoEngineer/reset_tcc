package br.com.cwi.reset.tcc.response;

import br.com.cwi.reset.tcc.dominio.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
public class FazerPedidoResponse {

    private Long id;

    private Long tempoEstimado;

    private Endereco enderecoEntrega;

    private BigDecimal valorTotal;

    private String statusPedido;
}
