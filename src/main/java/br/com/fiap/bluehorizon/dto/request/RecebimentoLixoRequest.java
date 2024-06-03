package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record RecebimentoLixoRequest(

        @NotNull(message = "A data é obrigatória")
        @PastOrPresent(message = "A data deve ser válida")
        LocalDate dataRecebimento,

        @Valid
        AbstractRequest perfil,

        @Valid
        AbstractRequest pessoa,

        @Valid
        AbstractRequest pontosColeta,

        @Valid
        AbstractRequest tiposLixo
) {
}
