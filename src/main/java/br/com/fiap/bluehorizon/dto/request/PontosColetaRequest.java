package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PontosColetaRequest(

        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        @NotNull
        String nome,

        @NotNull
        @Size( min = 2, max = 2, message = "O estado deve ser apenas siglas")
        String estado,

        @NotNull
        @Size(min = 2, max = 50, message = "O nome do gerente deve ter entre 2 e 50 caracteres")
        String gerente
) {
}
