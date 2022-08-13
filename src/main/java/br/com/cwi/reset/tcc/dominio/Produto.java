package br.com.cwi.reset.tcc.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Obrigatório inserir título")
    private String titulo;

    @NotBlank(message = "Obrigatório inserir descrição do produto")
    private String descricao;

    @NotNull(message = "Obrigatório inserir valor do produto")
    @Positive(message = "Não é possível cadastrar um produto com valor inferior a zero")
    private BigDecimal valor;

    private String urlFoto;

    @Valid
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Integer tempoPreparo;

    @ManyToOne
    @JoinColumn(name = "id_estabelecimento")
    private Estabelecimento estabelecimento;

}
