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
        return repo.findAll();
    }

    @Override
    public Collection<VoluntarioPerfil> findAll(Example<VoluntarioPerfil> example) {
        return repo.findAll(example);
    }

    @Override
    public VoluntarioPerfil findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public VoluntarioPerfil save(VoluntarioPerfil e) {
        return repo.save(e);
    }

    @Override
    public VoluntarioPerfil toEntity(VoluntarioPerfilRequest dto) {
        return VoluntarioPerfil.builder()
                .qntdLixo(dto.qntdLixo())
                .build();
    }

    @Override
    public VoluntarioPerfilResponse toResponse(VoluntarioPerfil e) {
        return VoluntarioPerfilResponse.builder()
                .id(e.getId())
                .qntdLixo(e.getQntdLixo())
                .build();
    }
}
