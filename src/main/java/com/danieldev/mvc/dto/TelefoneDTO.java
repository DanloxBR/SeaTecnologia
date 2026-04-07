package com.danieldev.mvc.dto;

import com.danieldev.mvc.entity.TipoTelefone;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class TelefoneDTO {

    @NotNull
    private TipoTelefone tipo;

    @NotBlank
    @Pattern(regexp = "\\d{10,11}")
    private String numero;
}