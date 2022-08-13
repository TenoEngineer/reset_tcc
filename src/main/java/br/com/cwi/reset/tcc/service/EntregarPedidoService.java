package br.com.cwi.reset.tcc.service;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import br.com.cwi.reset.tcc.exceptions.PedidoEmPreparoException;
import br.com.cwi.reset.tcc.exceptions.PedidoNaoEncontradoException;
import br.com.cwi.reset.tcc.exceptions.SemEntregadoresException;
import br.com.cwi.reset.tcc.repository.EntregadorRepository;
import br.com.cwi.reset.tcc.repository.PedidoRepository;
import br.com.cwi.reset.tcc.response.BuscarPedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntregarPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    public Entregador entregarPedido(final Long id) {

        Pedido pedidoBuscado = pedidoRepository.findById(id).orElse(null);
        if (pedidoBuscado == null) {
            throw new PedidoNaoEncontradoException();
        }

        if (!(pedidoBuscado.getStatusPedido().equals(StatusPedido.EM_PREPARO))) {
            throw new PedidoEmPreparoException();
        }

        pedidoBuscado.setHorarioSaiuParaEntrega(LocalDateTime.now());
        pedidoBuscado.setStatusPedido(StatusPedido.SAIU_PARA_ENTREGA);

        encontrarEntregadorFinal(pedidoBuscado);

        pedidoRepository.save(pedidoBuscado);

        return pedidoBuscado.getEntregador();
    }

    private boolean encontrarEntregador(final Pedido pedido) {

        List<Entregador> entregadores = new ArrayList<>(entregadorRepository.findAll());

        for (Entregador entregador : entregadores) {
            if (entregador.getDisponivel()) {
                if (pedido.getEntregador() == null) {
                    pedido.setEntregador(entregador);
                    pedido.getEntregador().setDisponivel(false);
                    pedidoRepository.save(pedido);
                    return true;
                }
            }
        }
        return false;
    }

    private void encontrarEntregadorFinal(final Pedido pedido) {

        if (!(encontrarEntregador(pedido))) {
            throw new SemEntregadoresException();
        }
    }
}
