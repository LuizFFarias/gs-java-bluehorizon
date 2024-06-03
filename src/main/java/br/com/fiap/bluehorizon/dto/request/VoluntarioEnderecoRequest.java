package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

public record VoluntarioEnderecoRequest(


        @Size(min = 8, max = 8, message = "O CEP deve conter 8 caracteres sem simbolos ou letras")
        @Pattern(regexp = "\\d{8}", message = "O CEP deve conter apenas números")
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
