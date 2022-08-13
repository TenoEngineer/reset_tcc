package br.com.cwi.reset.tcc.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Obrigatório inserir o CNPJ da empresa")
    private String cnpj;

    @NotBlank(message = "Obrigatório inserir o nome fantasia da empresa")
    private String nomeFantasia;

    @NotBlank(message = "Obrigatório inserir a razão social da empresa")
    private String razaoSocial;

    @NotEmpty(message = "Obrigatório inserir os horários de funcionamento da empresa")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estabelecimento")
    private List<HorarioFuncionamento> horariosFuncionamento;

    @NotEmpty(message = "Obrigatório inserir as formas de pagamento da empresa")
    @ElementCollection(targetClass = FormaPagamento.class)
    @JoinTable(name = "FORMAS_PAGAMENTO", joinColumns = @JoinColumn(name = "cnpj"))
    @Enumerated(EnumType.STRING)
    private List<FormaPagamento> formasPagamento;

    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estabelecimento")
    private List<Endereco> enderecos;

}
