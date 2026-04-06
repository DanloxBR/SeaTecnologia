package com.danieldev.mvc.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class ClienteDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$")
    private String nome;

    @NotBlank
    private String cpf;

    @Valid
    private EnderecoDTO endereco;

    @Valid
    @NotEmpty
    @Size(max = 5)
    private List<TelefoneDTO> telefones;

    @NotEmpty
    private List<@Email String> emails;
}