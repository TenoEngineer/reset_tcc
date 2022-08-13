package br.com.cwi.reset.tcc.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario_solicitante")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "id_estabelecimento")
    private Estabelecimento estabelecimento;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_pedido")
    private List<ItemPedido> itensPedido;

    @Valid
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime horarioSolicitacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime horarioSaiuParaEntrega;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime horarioEntrega;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime horarioCancelamento;

    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "id_entregador")
    private Entregador entregador;
}
