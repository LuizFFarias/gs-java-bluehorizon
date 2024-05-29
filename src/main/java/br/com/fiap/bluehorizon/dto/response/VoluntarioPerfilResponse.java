package br.com.fiap.bluehorizon.dto.response;

import lombok.Builder;

@Builder
public record VoluntarioPerfilResponse(

        Long id,
        Float qntdLixo
) {
}
