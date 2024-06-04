package br.com.fiap.bluehorizon.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RecebimentoLixoResponse(

        Long id,
        LocalDate dataRecebimento,
        VoluntarioPerfilResponse perfil,
        VoluntarioPessoaResponse pessoa,
        PontosColetaResponse pontosColeta,
        TiposLixoResponse tiposLixo
) {
}
