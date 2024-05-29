package br.com.fiap.bluehorizon.dto.response;

import java.time.LocalDate;

public record RecebimentoLixoResponse(

        Long id,
        LocalDate dataRecebimento,
        VoluntarioPerfilResponse perfil,
        VoluntarioPessoaResponse pessoa,
        PontosColetaResponse pontosColeta,
        TiposLixoResponse tiposLixo
) {
}
