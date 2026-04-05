package com.danieldev.mvc.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneDTO {

    @NotBlank
    private String tipo;

    @NotBlank
    private String numero;
}