package br.com.cwi.reset.tcc.mapper;

import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.response.FazerPedidoResponse;

import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
public class PedidoResponseMapper {

    public FazerPedidoResponse mapear(final Pedido pedido) {

        Long tempoEstimado = ChronoUnit.MINUTES.between(pedido.getHorarioSolicitacao(), pedido.getHorarioEntrega());

        return new FazerPedidoResponse(pedido.getId(), tempoEstimado, pedido.getEnderecoEntrega(),
                pedido.getValorTotal(), pedido.getStatusPedido().getDescricao());
    }
}
