package br.com.fiap.bluehorizon.service;

import br.com.fiap.bluehorizon.dto.request.VoluntarioPerfilRequest;
import br.com.fiap.bluehorizon.dto.request.VoluntarioPessoaRequest;
import br.com.fiap.bluehorizon.dto.response.VoluntarioPessoaResponse;
import br.com.fiap.bluehorizon.entity.VoluntarioPessoa;
import br.com.fiap.bluehorizon.repository.VoluntarioPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VoluntarioPessoaService implements ServiceDTO<VoluntarioPessoa, VoluntarioPessoaRequest, VoluntarioPessoaResponse>{

   @Autowired
   private VoluntarioPessoaRepository repo;

   @Autowired
   private VoluntarioEnderecoService enderecoService;

   @Autowired
   private VoluntarioPerfilService perfilService;

    @Override
    public Collection<VoluntarioPessoa> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<VoluntarioPessoa> findAll(Example<VoluntarioPessoa> example) {
        return repo.findAll(example);
    }

    @Override
    public VoluntarioPessoa findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public VoluntarioPessoa save(VoluntarioPessoa e) {
        return repo.save(e);
    }

    @Override
    public VoluntarioPessoa toEntity(VoluntarioPessoaRequest dto) {

        var endereco = enderecoService.findById(dto.endereco().id());
        var perfil = perfilService.findById(dto.perfil().id());

        /*
        TODO fazer uma verificação de idade para liberar apenas maiores
         */

        return VoluntarioPessoa.builder()
                .cpf(dto.cpf())
                .nome(dto.nome())
                .dtNascimento(dto.dtNascimento())
                .senha(dto.senha())
                .endereco(endereco)
                .perfil(perfil)
                .build();
    }

    @Override
    public VoluntarioPessoaResponse toResponse(VoluntarioPessoa e) {

        var endereco = enderecoService.toResponse(e.getEndereco());
        var perfil = perfilService.toResponse(e.getPerfil());

        return VoluntarioPessoaResponse.builder()
                .id(e.getId())
                .cpf(e.getCpf())
                .nome(e.getNome())
                .dtNascimento(e.getDtNascimento())
                .endereco(endereco)
                .perfil(perfil)
                .build();
    }
}