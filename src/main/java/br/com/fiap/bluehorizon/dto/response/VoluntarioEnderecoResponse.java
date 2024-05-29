package br.com.fiap.bluehorizon.dto.response;

public record VoluntarioEnderecoResponse(
        Long id,
        String cpf,
        String numero,
        String rua,
        String bairro,
        String cidade,
        String estado,
        String pais

) {
}
