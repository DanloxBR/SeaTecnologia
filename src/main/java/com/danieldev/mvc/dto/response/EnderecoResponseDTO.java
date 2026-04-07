package com.danieldev.mvc.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnderecoResponseDTO {

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;
}