package br.com.fiap.bluehorizon.service;


import br.com.fiap.bluehorizon.dto.request.SituacaoPraiaRequest;
import br.com.fiap.bluehorizon.dto.response.SituacaoPraiaResponse;
import br.com.fiap.bluehorizon.entity.SituacaoPraia;
import br.com.fiap.bluehorizon.repository.SituacaoPraiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SituacaoPraiaService implements ServiceDTO<SituacaoPraia, SituacaoPraiaRequest, SituacaoPraiaResponse>{

      @Autowired
      private SituacaoPraiaRepository repo;

    @Override
    public Collection<SituacaoPraia> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<SituacaoPraia> findAll(Example<SituacaoPraia> example) {
        return repo.findAll(example);
    }

    @Override
    public SituacaoPraia findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public SituacaoPraia save(SituacaoPraia e) {
        return repo.save(e);
    }

    @Override
    public SituacaoPraia toEntity(SituacaoPraiaRequest dto) {
        return SituacaoPraia.builder()
                .nome(dto.nome())
                .cidade(dto.cidade())
                .nivelSujeira(dto.nivelSujeira())
                .build();
    }

    @Override
    public SituacaoPraiaResponse toResponse(SituacaoPraia e) {
        return SituacaoPraiaResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .cidade(e.getCidade())
                .nivelSujeira(e.getNivelSujeira())
                .build();
    }
}
