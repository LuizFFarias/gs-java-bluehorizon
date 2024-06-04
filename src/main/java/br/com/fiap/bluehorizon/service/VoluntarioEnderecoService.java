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
        try {
            return repo.findAll();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public Collection<VoluntarioEndereco> findAll(Example<VoluntarioEndereco> example) {
        try {
            return repo.findAll(example);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public VoluntarioEndereco findById(Long id) {
        try {
            return repo.findById(id).orElse(null);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public VoluntarioEndereco save(VoluntarioEndereco e) {
        try {
            return repo.save(e);
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }
    }

    @Override
    public VoluntarioEndereco toEntity(VoluntarioEnderecoRequest dto) {
        try {
            return VoluntarioEndereco.builder()
                    .cep(dto.cep())
                    .numero(dto.numero())
                    .rua(dto.rua())
                    .bairro(dto.bairro())
                    .cidade(dto.cidade())
                    .estado(dto.estado())
                    .pais(dto.pais())
                    .build();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }

    }

    @Override
    public VoluntarioEnderecoResponse toResponse(VoluntarioEndereco e) {

        try {
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
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }

    }
}
