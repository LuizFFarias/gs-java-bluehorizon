package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VoluntarioPerfilRequest(

        @Size(min = 2, max = 12, message = "A quantidade de lixo deve ter entre 2 e 12 caracteres")
        Float qntdLixo
) {
}
