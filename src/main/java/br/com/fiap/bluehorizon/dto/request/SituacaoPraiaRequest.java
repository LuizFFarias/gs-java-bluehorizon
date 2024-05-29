package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SituacaoPraiaRequest(

        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        @NotNull
        String nome,

        @NotNull
        Integer nivelSujeira,

        @Size(min = 2, max = 50, message = "A cidade deve ter entre 2 e 50 caracteres")
        @NotNull
        String cidade
) {
}
