package br.com.cwi.reset.tcc.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
public class Endereco {

    public static final String REGEX_CEP = "[0-9]{5}-[0-9]{3}";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @Pattern(regexp = REGEX_CEP, message = "CEP deve seguir o padrão 99999-99")
    private String cep;

    @NotBlank(message = "Obrigatório inserir logradouro")
    private String logradouro;

    @NotBlank(message = "Obrigatório inserir número")
    private String numero;

    private String complemento;

    @NotBlank(message = "Obrigatório inserir bairro")
    private String bairro;

    @NotBlank(message = "Obrigatório inserir cidade")
    private String cidade;

    @NotBlank
    private String estado;

}
