package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import br.com.cwi.reset.tcc.exceptions.PedidoNaoEncontradoException;
import br.com.cwi.reset.tcc.exceptions.PedidoSaiuParaEntregaException;
import br.com.cwi.reset.tcc.repository.PedidoRepository;
import br.com.cwi.reset.tcc.response.BuscarPedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FinalizarPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void finalizarPedido(final Long id){

        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido==null){
            throw new PedidoNaoEncontradoException();
        }


        if(!(pedido.getStatusPedido().equals(StatusPedido.SAIU_PARA_ENTREGA))){
            throw new PedidoSaiuParaEntregaException();
        }

        pedido.setStatusPedido(StatusPedido.ENTREGUE);
        pedido.setHorarioEntrega(LocalDateTime.now());
        pedido.getEntregador().setDisponivel(true);
        pedidoRepository.save(pedido);
    }
}
