package br.com.cwi.reset.tcc.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ItemPedidoRequest {

    private Long idProduto;

    private Integer quantidade;
}
