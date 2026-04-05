package com.danieldev.mvc.integration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViaCepResponse {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}