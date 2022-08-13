package br.com.cwi.reset.tcc.mapper;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.response.BuscarItemPedidoResponse;
import br.com.cwi.reset.tcc.response.BuscarPedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class BuscarPedidoResponseMapper {

    @Autowired
    private BuscarItemPedidoResponseMapper buscarItemPedidoResponseMapper;

    public BuscarPedidoResponse mapear(final Pedido pedido){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        List<BuscarItemPedidoResponse> itensPedido = buscarItemPedidoResponseMapper.mapear(pedido.getItensPedido());


        return new BuscarPedidoResponse(pedido.getSolicitante().getNome(), pedido.getEnderecoEntrega(),
                pedido.getEstabelecimento().getNomeFantasia(), itensPedido,
                pedido.getValorTotal(), pedido.getStatusPedido().getDescricao() ,pedido.getEntregador(), dtf.format(pedido.getHorarioEntrega()));
    }
}
