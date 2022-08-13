package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import br.com.cwi.reset.tcc.exceptions.LimiteTempoCancelamentoException;
import br.com.cwi.reset.tcc.exceptions.PedidoEmPreparoException;
import br.com.cwi.reset.tcc.exceptions.PedidoNaoEncontradoException;
import br.com.cwi.reset.tcc.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class CancelarPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    static final long LIMITE_CANCELAMENTO_TEMPO = 10;

    public void cancelarPedido(final Long id) {

        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            throw new PedidoNaoEncontradoException();
        }

        if (!(pedido.getStatusPedido().equals(StatusPedido.EM_PREPARO))) {
            throw new PedidoEmPreparoException();
        }

        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedido.setHorarioCancelamento(LocalDateTime.now());

        long diferencaMinutos = ChronoUnit.MINUTES.between(pedido.getHorarioSolicitacao(), pedido.getHorarioCancelamento());

        if (diferencaMinutos >= LIMITE_CANCELAMENTO_TEMPO) {
            throw new LimiteTempoCancelamentoException();
        }

        pedidoRepository.save(pedido);
    }
}
