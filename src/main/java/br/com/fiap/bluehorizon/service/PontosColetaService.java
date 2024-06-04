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
        try {
            return repo.findAll();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public Collection<PontosColeta> findAll(Example<PontosColeta> example) {

        try {
            return repo.findAll(example);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public PontosColeta findById(Long id) {
        try {
            return repo.findById(id).orElse(null);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public PontosColeta save(PontosColeta e) {
        try {
            return repo.save(e);
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }
    }

    @Override
    public PontosColeta toEntity(PontosColetaRequest dto) {
        try {
            return PontosColeta.builder()
                    .nome(dto.nome())
                    .estado(dto.estado())
                    .gerente(dto.gerente())
                    .build();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public PontosColetaResponse toResponse(PontosColeta e) {
        try {
            return PontosColetaResponse.builder()
                    .id(e.getId())
                    .nome(e.getNome())
                    .estado(e.getEstado())
                    .gerente(e.getGerente())
                    .build();
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }

    }
}
