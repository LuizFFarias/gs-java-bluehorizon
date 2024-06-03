package br.com.fiap.bluehorizon.service;

import br.com.fiap.bluehorizon.dto.request.VoluntarioEnderecoRequest;
import br.com.fiap.bluehorizon.dto.request.VoluntarioPerfilRequest;
import br.com.fiap.bluehorizon.dto.response.VoluntarioPerfilResponse;
import br.com.fiap.bluehorizon.entity.VoluntarioPerfil;
import br.com.fiap.bluehorizon.repository.VoluntarioPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VoluntarioPerfilService implements ServiceDTO<VoluntarioPerfil, VoluntarioPerfilRequest, VoluntarioPerfilResponse>{

    @Autowired
    private VoluntarioPerfilRepository repo;

    @Override
    public Collection<VoluntarioPerfil> findAll() {
        try {
            return repo.findAll();
        } catch (Exception e){
        System.out.println("Erro: " + e);
        return null;
    }
    }

    @Override
    public Collection<VoluntarioPerfil> findAll(Example<VoluntarioPerfil> example) {
        try {
            return repo.findAll(example);
        } catch (Exception e){
        System.out.println("Erro: " + e);
        return null;
    }
    }

    @Override
    public VoluntarioPerfil findById(Long id) {
        try {
            return repo.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public VoluntarioPerfil save(VoluntarioPerfil e) {
        try {
            return repo.save(e);
        } catch (Exception er) {
            System.out.println("Erro: " + er);
            return null;
        }
    }

    @Override
    public VoluntarioPerfil toEntity(VoluntarioPerfilRequest dto) {
        try {
            return VoluntarioPerfil.builder()
                    .qntdLixo(dto.qntdLixo())
                    .build();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }

    }

    @Override
    public VoluntarioPerfilResponse toResponse(VoluntarioPerfil e) {
        try {
            return VoluntarioPerfilResponse.builder()
                    .id(e.getId())
                    .qntdLixo(e.getQntdLixo())
                    .build();
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }
    }
}
