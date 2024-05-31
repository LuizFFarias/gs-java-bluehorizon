package br.com.fiap.bluehorizon.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record VoluntarioPessoaRequest(

        @NotNull
        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        String nome,

        @NotNull
        @CPF(message = "O cpf não é válido")
        String cpf,

        @NotNull(message = "A data não pode ser nula")
        @Past(message = "A data não é válida")
        LocalDate dtNascimento,

        @NotNull
        @Size(min = 8, max = 15,message = "A senha deve ter entre 8 e 15 caracteres")
        String senha,

        @Valid
        AbstractRequest endereco,

        @Valid
        AbstractRequest perfil

) {
}
