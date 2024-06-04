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
        try {
            return repo.findAll();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public Collection<TiposLixo> findAll(Example<TiposLixo> example) {
        try {
            return repo.findAll(example);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public TiposLixo findById(Long id) {

        try {
            return repo.findById(id).orElse(null);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public TiposLixo save(TiposLixo e) {

        try {
            return repo.save(e);
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }
    }

    @Override
    public TiposLixo toEntity(TiposLixoRequest dto) {
        try {
            return TiposLixo.builder()
                    .nome(dto.nome())
                    .valorKg(dto.valorKg())
                    .build();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public TiposLixoResponse toResponse(TiposLixo e) {
        try {
            return TiposLixoResponse.builder()
                    .id(e.getId())
                    .nome(e.getNome())
                    .valorKg(e.getValorKg())
                    .build();
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }
    }
}
