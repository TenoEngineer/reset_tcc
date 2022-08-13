package br.com.cwi.reset.tcc.request;

import br.com.cwi.reset.tcc.dominio.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CriarProdutoRequest {

    private Long idEstabelecimento;
    private String titulo;
    private String descricao;
    private BigDecimal valor;
    private String urlFoto;
    private Categoria categoria;
    private Integer tempoPreparo;

}
