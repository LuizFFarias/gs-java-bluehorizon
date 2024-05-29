package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VoluntarioEnderecoRequest(


        @Size(min = 8, max = 8, message = "O CEP deve conter 8 caracteres")
        @NotNull(message = "O CEP é obrigatório")
        String cep,

        String numero,

        String rua,

        String bairro,

        String cidade,

        String estado,

        String pais

) {
}
