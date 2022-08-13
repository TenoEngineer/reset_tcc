package br.com.cwi.reset.tcc.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @NotBlank(message = "Obrigatório inserir um CPF")
    @CPF
    private String cpf;

    @NotBlank(message = "Obrigatório inserir um nome")
    private String nome;

    @NotBlank(message = "Obrigatório inserir um e-mail")
    @Email
    private String email;

    @NotBlank(message = "Obrigatório inserir uma senha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")

    private List<Endereco> enderecos;

}
