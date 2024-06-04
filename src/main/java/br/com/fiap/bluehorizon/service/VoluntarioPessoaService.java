package br.com.fiap.bluehorizon.service;

import br.com.fiap.bluehorizon.dto.request.VoluntarioPerfilRequest;
import br.com.fiap.bluehorizon.dto.request.VoluntarioPessoaRequest;
import br.com.fiap.bluehorizon.dto.response.VoluntarioPessoaResponse;
import br.com.fiap.bluehorizon.entity.VoluntarioPessoa;
import br.com.fiap.bluehorizon.repository.VoluntarioPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        try {
            return repo.findAll();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }

    }

    @Override
    public Collection<VoluntarioPessoa> findAll(Example<VoluntarioPessoa> example) {
        try {
            return repo.findAll(example);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }

    }

    @Override
    public VoluntarioPessoa findById(Long id) {
        try {
            return repo.findById(id).orElse(null);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }

    }

    @Override
    public VoluntarioPessoa save(VoluntarioPessoa e) {
        try {
            return repo.save(e);
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }

    }

    @Override
    public VoluntarioPessoa toEntity(VoluntarioPessoaRequest dto) {

        try {
            var endereco = enderecoService.findById(dto.endereco().id());
            var perfil = perfilService.findById(dto.perfil().id());

            return VoluntarioPessoa.builder()
                    .cpf(dto.cpf())
                    .nome(dto.nome())
                    .dtNascimento(dto.dtNascimento())
                    .senha(dto.senha())
                    .endereco(endereco)
                    .perfil(perfil)
                    .build();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }

    }

    @Override
    public VoluntarioPessoaResponse toResponse(VoluntarioPessoa e) {

        try {
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
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }

    }
}
