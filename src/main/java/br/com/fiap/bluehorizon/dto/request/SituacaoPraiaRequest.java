package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record SituacaoPraiaRequest(

        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        @NotNull(message = "O nome é obrigatório")
        String nome,

        @PositiveOrZero
        @NotNull(message = "O nivel de sujeira é obrigatório")
        Integer nivelSujeira,

        @Size(min = 2, max = 50, message = "A cidade deve ter entre 2 e 50 caracteres")
        @NotNull(message = "A cidade é obrigatória")
        String cidade
) {
}
