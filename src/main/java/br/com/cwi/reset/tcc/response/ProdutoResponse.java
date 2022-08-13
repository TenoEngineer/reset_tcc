package br.com.cwi.reset.tcc.response;

import br.com.cwi.reset.tcc.dominio.Categoria;
import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoResponse{

    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    @PositiveOrZero
    private BigDecimal valor;

    private String urlFoto;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Integer tempoPreparo;

    private String cnpj;

}
