package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TiposLixoRequest(

        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        @NotNull(message = "O nome é obrigatório")
        String nome,

        @Size(min = 1, max = 6, message = "O valor deve ter entre 1 e 6 caracteres")
        @NotNull(message = "O valor é obrigatório")
        BigDecimal valorKg
) {
}
