package br.com.fiap.bluehorizon.service;

import br.com.fiap.bluehorizon.dto.request.RecebimentoLixoRequest;
import br.com.fiap.bluehorizon.dto.response.RecebimentoLixoResponse;
import br.com.fiap.bluehorizon.entity.RecebimentoLixo;
import br.com.fiap.bluehorizon.repository.RecebimentoLixoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class RecebimentoLixoService implements ServiceDTO<RecebimentoLixo, RecebimentoLixoRequest, RecebimentoLixoResponse>{

    @Autowired
    private RecebimentoLixoRepository repo;

    @Autowired
    private VoluntarioPerfilService perfilService;

    @Autowired
    private VoluntarioPessoaService pessoaService;

    @Autowired
    private PontosColetaService coletaService;

    @Autowired
    private TiposLixoService lixoService;


    @Override
    public Collection<RecebimentoLixo> findAll() {
        try {
            return repo.findAll();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public Collection<RecebimentoLixo> findAll(Example<RecebimentoLixo> example) {
        try {
            return repo.findAll(example);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public RecebimentoLixo findById(Long id) {

        try {
            return repo.findById(id).orElse(null);
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public RecebimentoLixo save(RecebimentoLixo e) {
        try {
            return repo.save(e);
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }
    }

    @Override
    public RecebimentoLixo toEntity(RecebimentoLixoRequest dto) {

        try {
            var perfil = perfilService.findById(dto.perfil().id());
            var pessoa = pessoaService.findById(dto.pessoa().id());
            var coleta = coletaService.findById(dto.pontosColeta().id());
            var tiposLixo = lixoService.findById(dto.tiposLixo().id());

            if (!Objects.equals(pessoa.getPerfil().getId(), perfil.getId())) return null;

            return RecebimentoLixo.builder()
                    .dataRecebimento(dto.dataRecebimento())
                    .perfil(perfil)
                    .pessoa(pessoa)
                    .pontosColeta(coleta)
                    .tiposLixo(tiposLixo)
                    .build();
        } catch (Exception e){
            System.out.println("Erro: " + e);
            return null;
        }
    }

    @Override
    public RecebimentoLixoResponse toResponse(RecebimentoLixo e) {

        try {
            var perfil = perfilService.toResponse(e.getPerfil());
            var pessoa = pessoaService.toResponse(e.getPessoa());
            var coleta = coletaService.toResponse(e.getPontosColeta());
            var tiposLixo = lixoService.toResponse(e.getTiposLixo());

            return RecebimentoLixoResponse.builder()
                    .id(e.getId())
                    .dataRecebimento(e.getDataRecebimento())
                    .perfil(perfil)
                    .pessoa(pessoa)
                    .pontosColeta(coleta)
                    .tiposLixo(tiposLixo)
                    .build();
        } catch (Exception er){
            System.out.println("Erro: " + er);
            return null;
        }
    }
}
