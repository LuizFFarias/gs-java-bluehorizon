package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PontosColetaRequest(

        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        @NotNull(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "O estado é obrigatório")
        @Size( min = 2, max = 2, message = "O estado deve ser apenas siglas")
        String estado,

        @NotNull(message = "O nome do gerente é obrigatório")
        @Size(min = 2, max = 50, message = "O nome do gerente deve ter entre 2 e 50 caracteres")
        String gerente
) {
}
