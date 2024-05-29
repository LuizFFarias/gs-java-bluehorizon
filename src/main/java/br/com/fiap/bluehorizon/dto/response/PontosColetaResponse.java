package br.com.fiap.bluehorizon.dto.response;

public record PontosColetaResponse(

        Long id,
        String nome,
        String estado,
        String gerente

) {
}
