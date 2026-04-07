package com.danieldev.mvc.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TelefoneResponseDTO {

    private String numero;
    private String tipo;
}
