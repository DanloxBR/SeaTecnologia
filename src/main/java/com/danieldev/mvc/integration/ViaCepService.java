package com.danieldev.mvc.integration;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
    private final String URL = "https://viacep.com.br/ws/%s/json/";

    public ViaCepResponse buscarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(URL, cep.replaceAll("\\D", ""));
        return restTemplate.getForObject(url, ViaCepResponse.class);
    }
}