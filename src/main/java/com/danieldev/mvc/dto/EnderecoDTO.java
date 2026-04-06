package com.danieldev.mvc.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EnderecoDTO {

    @NotBlank
    private String cep;

    private String complemento;
}