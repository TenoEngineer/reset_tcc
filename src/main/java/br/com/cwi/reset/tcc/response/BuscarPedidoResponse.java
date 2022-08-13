package br.com.cwi.reset.tcc.response;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.dominio.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BuscarPedidoResponse {

    private String nomeUsuario;
    private Endereco enderecoEntrega;
    private String nomeFantasiaEstabelecimento;
    private List<BuscarItemPedidoResponse> itemPedidos;
    private BigDecimal valorTotal;
    private String statusPedido;
    private Entregador entregador;
    private String horarioEntrega;

}
