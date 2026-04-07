package com.danieldev.mvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "\\d{10,11}")
    private String numero;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;
}