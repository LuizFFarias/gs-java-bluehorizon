package br.com.fiap.bluehorizon.service;

import br.com.fiap.bluehorizon.dto.request.VoluntarioEnderecoRequest;
import br.com.fiap.bluehorizon.dto.response.VoluntarioEnderecoResponse;
import br.com.fiap.bluehorizon.entity.VoluntarioEndereco;
import br.com.fiap.bluehorizon.repository.VoluntarioEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VoluntarioEnderecoService implements ServiceDTO<VoluntarioEndereco, VoluntarioEnderecoRequest, VoluntarioEnderecoResponse>{

    @Autowired
    private VoluntarioEnderecoRepository repo;

    @Override
    public Collection<VoluntarioEndereco> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<VoluntarioEndereco> findAll(Example<VoluntarioEndereco> example) {
        return repo.findAll(example);
    }

    @Override
    public VoluntarioEndereco findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public VoluntarioEndereco save(VoluntarioEndereco e) {
        return repo.save(e);
    }

    @Override
    public VoluntarioEndereco toEntity(VoluntarioEnderecoRequest dto) {
        return VoluntarioEndereco.builder()
                .cep(dto.cep())
                .numero(dto.numero())
                .rua(dto.rua())
                .bairro(dto.bairro())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .pais(dto.pais())
                .build();
    }

    @Override
    public VoluntarioEnderecoResponse toResponse(VoluntarioEndereco e) {

        return VoluntarioEnderecoResponse.builder()
                .id(e.getId())
                .cep(e.getCep())
                .rua(e.getRua())
                .numero(e.getNumero())
                .bairro(e.getBairro())
                .cidade(e.getCidade())
                .estado(e.getEstado())
                .pais(e.getPais())
                .build();
    }
}
