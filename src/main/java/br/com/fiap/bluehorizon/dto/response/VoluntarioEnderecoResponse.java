package br.com.fiap.bluehorizon.dto.response;

import lombok.Builder;

@Builder
public record VoluntarioEnderecoResponse(
        Long id,
        String cep,
        String numero,
        String rua,
        String bairro,
        String cidade,
        String estado,
        String pais

) {
}
