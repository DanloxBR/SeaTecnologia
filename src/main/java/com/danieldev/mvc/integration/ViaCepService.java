package com.danieldev.mvc.integration;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
    private final String URL = "https://viacep.com.br/ws/%s/json/";

    public ViaCepResponse buscarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(String.format(URL, cep), ViaCepResponse.class);
    }
}