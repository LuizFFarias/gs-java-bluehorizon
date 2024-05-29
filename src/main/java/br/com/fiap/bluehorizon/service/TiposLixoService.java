package br.com.fiap.bluehorizon.service;

import br.com.fiap.bluehorizon.dto.request.TiposLixoRequest;
import br.com.fiap.bluehorizon.dto.response.TiposLixoResponse;
import br.com.fiap.bluehorizon.entity.TiposLixo;
import br.com.fiap.bluehorizon.repository.TiposLixoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TiposLixoService implements ServiceDTO<TiposLixo, TiposLixoRequest, TiposLixoResponse>{

    @Autowired
    private TiposLixoRepository repo;

    @Override
    public Collection<TiposLixo> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<TiposLixo> findAll(Example<TiposLixo> example) {
        return repo.findAll(example);
    }

    @Override
    public TiposLixo findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public TiposLixo save(TiposLixo e) {
        return repo.save(e);
    }

    @Override
    public TiposLixo toEntity(TiposLixoRequest dto) {
        return TiposLixo.builder()
                .nome(dto.nome())
                .valorKg(dto.valorKg())
                .build();
    }

    @Override
    public TiposLixoResponse toResponse(TiposLixo e) {
        return TiposLixoResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .valorKg(e.getValorKg())
                .build();
    }
}
