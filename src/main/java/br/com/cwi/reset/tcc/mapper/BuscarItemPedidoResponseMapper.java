package br.com.cwi.reset.tcc.mapper;

import br.com.cwi.reset.tcc.dominio.ItemPedido;
import br.com.cwi.reset.tcc.response.BuscarItemPedidoResponse;
import br.com.cwi.reset.tcc.response.BuscarPedidoResponse;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuscarItemPedidoResponseMapper {

    public List<BuscarItemPedidoResponse> mapear(final List<ItemPedido> itemPedidos){
        List<BuscarItemPedidoResponse> responses = new ArrayList<>();

        for (ItemPedido itemPedido : itemPedidos){
            BuscarItemPedidoResponse buscarPedidoResponse = new BuscarItemPedidoResponse(itemPedido.getProduto().getTitulo(),
                    itemPedido.getQuantidade());
            responses.add(buscarPedidoResponse);
        }
        return responses;
    }
}
