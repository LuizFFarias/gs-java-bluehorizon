package br.com.fiap.bluehorizon.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TiposLixoResponse(

        Long id,
        String nome,
        BigDecimal valorKg
) {
}
