package br.com.fiap.bluehorizon.dto.response;

import lombok.Builder;

@Builder
public record PontosColetaResponse(

        Long id,
        String nome,
        String estado,
        String gerente

) {
}
