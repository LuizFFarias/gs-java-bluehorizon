package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

public record VoluntarioPessoaRequest(

        @NotNull(message = "O nome é obrigatório")
        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        String nome,

        @NotNull(message = "O CPF é obrigatório")
        @CPF(message = "O cpf não é válido")
        @Pattern(regexp = "\\d{11}", message = "O CEP deve conter apenas números")
        String cpf,

        @NotNull(message = "A data é obrigatória")
        @Past(message = "A data não é válida")
        LocalDate dtNascimento,

        @NotNull(message = "A senha é obrigatória")
        @Size(min = 8, max = 15,message = "A senha deve ter entre 8 e 15 caracteres")
        String senha,

        @Valid
        AbstractRequest endereco,

        @Valid
        AbstractRequest perfil

) {
}
