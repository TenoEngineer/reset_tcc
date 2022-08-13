package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.exceptions.PedidoNaoEncontradoException;
import br.com.cwi.reset.tcc.mapper.BuscarPedidoResponseMapper;
import br.com.cwi.reset.tcc.repository.PedidoRepository;
import br.com.cwi.reset.tcc.response.BuscarPedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BuscarPedidoResponseMapper buscarPedidoResponseMapper;

    public BuscarPedidoResponse buscarPedido(final Long id){
        Pedido pedidoBuscado = pedidoRepository.findById(id).orElse(null);

        if (pedidoBuscado == null){
            throw new PedidoNaoEncontradoException();
        }

        if(pedidoBuscado.getStatusPedido().getDescricao().equals("Entregue")){
            pedidoBuscado.setHorarioEntrega(null);
        }

        pedidoRepository.save(pedidoBuscado);

        return buscarPedidoResponseMapper.mapear(pedidoBuscado);
    }
}
