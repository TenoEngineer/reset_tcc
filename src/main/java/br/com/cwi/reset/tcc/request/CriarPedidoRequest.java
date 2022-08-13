package br.com.cwi.reset.tcc.request;

import br.com.cwi.reset.tcc.dominio.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CriarPedidoRequest {

    private Long idEstabelecimento;
    private Long idUsuarioSolicitante;
    private Long idEnderecoEntrega;
    private List<ItemPedidoRequest> itens;
    private FormaPagamento formaPagamento;

}
