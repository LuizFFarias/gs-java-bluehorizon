package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TiposLixoRequest(

        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        @NotNull(message = "O nome é obrigatório")
        String nome,

        @Positive
        @NotNull(message = "O valor é obrigatório")
        BigDecimal valorKg
) {
}
