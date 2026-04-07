package com.danieldev.mvc.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private EnderecoResponseDTO endereco;
    private List<TelefoneResponseDTO> telefones;
    private List<String> emails;
}