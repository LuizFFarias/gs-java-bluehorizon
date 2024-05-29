package br.com.fiap.bluehorizon.dto.response;

import java.time.LocalDate;

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
