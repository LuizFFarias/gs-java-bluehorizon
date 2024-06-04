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
        try {
            return repo.findAll();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public Collection<SituacaoPraia> findAll(Example<SituacaoPraia> example) {
        try {
            return repo.findAll(example);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public SituacaoPraia findById(Long id) {

        try {
            return repo.findById(id).orElse(null);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public SituacaoPraia save(SituacaoPraia e) {
        try {
            return repo.save(e);
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }
    }

    @Override
    public SituacaoPraia toEntity(SituacaoPraiaRequest dto) {
        try {
            return SituacaoPraia.builder()
                    .nome(dto.nome())
                    .cidade(dto.cidade())
                    .nivelSujeira(dto.nivelSujeira())
                    .build();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public SituacaoPraiaResponse toResponse(SituacaoPraia e) {
        try {
            return SituacaoPraiaResponse.builder()
                    .id(e.getId())
                    .nome(e.getNome())
                    .cidade(e.getCidade())
                    .nivelSujeira(e.getNivelSujeira())
                    .build();
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }
    }
}
