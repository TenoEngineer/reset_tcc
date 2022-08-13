package br.com.cwi.reset.tcc.mapper;

import br.com.cwi.reset.tcc.dominio.ItemPedido;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import br.com.cwi.reset.tcc.request.ItemPedidoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemPedidoEntityMapper {

    @Autowired
    private ProdutoRepository repository;

    public List<ItemPedido> mapear(final List<ItemPedidoRequest> itemPedidoRequests){
        List<ItemPedido> itensPedido = new ArrayList<>();

        for(ItemPedidoRequest item : itemPedidoRequests){

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(repository.findById(item.getIdProduto()).orElse(null));
            itemPedido.setQuantidade(item.getQuantidade());

            itensPedido.add(itemPedido);
        }

        return itensPedido;
    }
}
