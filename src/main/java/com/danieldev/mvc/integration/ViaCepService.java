package com.danieldev.mvc.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private static final String URL = "https://viacep.com.br/ws/%s/json/";

    @Autowired
    private RestTemplate restTemplate;

    public ViaCepResponse buscarCep(String cep) {
        return restTemplate.getForObject(
                String.format(URL, cep),
                ViaCepResponse.class
        );
    }
}