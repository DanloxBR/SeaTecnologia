package com.danieldev.mvc.dto;

import com.danieldev.mvc.entity.TipoTelefone;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TelefoneDTO {

    @NotNull
    private TipoTelefone tipo;

    @NotBlank
    private String numero;
}