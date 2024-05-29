package br.com.fiap.bluehorizon.service;

import br.com.fiap.bluehorizon.dto.request.PontosColetaRequest;
import br.com.fiap.bluehorizon.dto.response.PontosColetaResponse;
import br.com.fiap.bluehorizon.entity.PontosColeta;
import br.com.fiap.bluehorizon.repository.PontosColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PontosColetaService implements ServiceDTO<PontosColeta, PontosColetaRequest, PontosColetaResponse> {


    @Autowired
    private PontosColetaRepository repo;

    @Override
    public Collection<PontosColeta> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<PontosColeta> findAll(Example<PontosColeta> example) {
        return repo.findAll(example);
    }

    @Override
    public PontosColeta findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public PontosColeta save(PontosColeta e) {
        return repo.save(e);
    }

    @Override
    public PontosColeta toEntity(PontosColetaRequest dto) {
        return PontosColeta.builder()
                .nome(dto.nome())
                .estado(dto.estado())
                .gerente(dto.gerente())
                .build();
    }

    @Override
    public PontosColetaResponse toResponse(PontosColeta e) {
        return PontosColetaResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .estado(e.getEstado())
                .gerente(e.getGerente())
                .build();
    }
}
