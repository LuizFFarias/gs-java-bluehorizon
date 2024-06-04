package br.com.fiap.bluehorizon.dto.response;

import lombok.Builder;

@Builder
public record SituacaoPraiaResponse(

        Long id,
        String nome,
        String cidade,
        Integer nivelSujeira

) {
}
