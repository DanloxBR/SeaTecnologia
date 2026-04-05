package com.danieldev.mvc.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "Nome deve conter apenas letras e espaços")
    private String nome;

    @NotBlank
    private String cpf;

    @Valid
    @NotNull
    private EnderecoDTO endereco;

    @Valid
    @NotEmpty(message = "Deve ter pelo menos um telefone")
    private List<TelefoneDTO> telefones;

    @NotEmpty(message = "Deve ter pelo menos um email")
    private List<@Email(message = "Email inválido") String> emails;
}