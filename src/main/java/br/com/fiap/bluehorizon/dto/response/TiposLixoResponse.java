package br.com.fiap.bluehorizon.dto.response;

import java.math.BigDecimal;

public record TiposLixoResponse(

        Long id,
        String nome,
        BigDecimal valorKg
) {
}
