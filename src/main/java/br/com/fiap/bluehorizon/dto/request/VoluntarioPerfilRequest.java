package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record VoluntarioPerfilRequest(

        @PositiveOrZero
        Float qntdLixo
) {
}
