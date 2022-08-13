package br.com.cwi.reset.tcc.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
public class Entregador {

    public static final String REGEX_PLACA_VEICULO = ("([A-Z]{3}[ ])([0-9]{1}[A-Z]{1}[0-9]{2})|([A-Z]{3}[ ])([0-9]{4})");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @NotBlank
    @Pattern(regexp = REGEX_PLACA_VEICULO, message = "Placa do veículo deve seguir o padrão 'AAA 9999' ou 'AAA 9A99'")
    private String placaVeiculo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean disponivel = true;


}
