package br.com.fiap.bluehorizon.dto.response;

public record SituacaoPraiaResponse(

        Long id,
        String nome,
        String cidade,
        Integer nivelSujeira

) {
}
