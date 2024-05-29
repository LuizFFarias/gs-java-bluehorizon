package br.com.fiap.bluehorizon.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record VoluntarioPessoaResponse(

        Long id,
        String cpf,
        String nome,
        LocalDate dtNascimento,
        String senha,
        VoluntarioEnderecoResponse endereco,
        VoluntarioPerfilResponse perfil
) {
}
