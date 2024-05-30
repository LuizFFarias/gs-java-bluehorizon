package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.RecebimentoLixoRequest;
import br.com.fiap.bluehorizon.dto.response.RecebimentoLixoResponse;
import br.com.fiap.bluehorizon.entity.*;
import br.com.fiap.bluehorizon.service.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping(value = "/recebimento-lixo")
public class RecebimentoLixoResource implements ResourceDTO<RecebimentoLixoRequest, RecebimentoLixoResponse>{

    @Autowired
    private RecebimentoLixoService service;

    @Autowired
    private VoluntarioPerfilService perfilService;

    @Autowired
    private VoluntarioPessoaService pessoaService;

    @Autowired
    private PontosColetaService coletaService;

    @Autowired
    private TiposLixoService tiposLixoService;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<RecebimentoLixoResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<RecebimentoLixoResponse> save(@RequestBody @Valid RecebimentoLixoRequest r) {
        var entity = service.toEntity(r);
        entity = service.save(entity);

        var response = service.toResponse(entity);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Collection<RecebimentoLixoResponse>> findAll(
            @RequestParam(name = "dataRecebimento", required = false) LocalDate dataRecebimento,
            @RequestParam(name = "perfil.qntdLixo", required = false) Float qntdLixo,
            @RequestParam(name = "pessoa.cpf", required = false) String cpf,
            @RequestParam(name = "pontosColeta.nome", required = false) String nomePtColeta,
            @RequestParam(name = "pontosColeta.estado", required = false) String estado,
            @RequestParam(name = "tiposLixo.nome", required = false) String nomeTpLixo,
            @RequestParam(name = "tiposLixo.valorKg", required = false)BigDecimal valorKg
            ){
        var perfil = VoluntarioPerfil.builder()
                .qntdLixo(qntdLixo)
                .build();

        var pessoa = VoluntarioPessoa.builder()
                .cpf(cpf)
                .build();

        var pontosColeta = PontosColeta.builder()
                .nome(nomePtColeta)
                .estado(estado)
                .build();

        var tiposLixo = TiposLixo.builder()
                .nome(nomeTpLixo)
                .valorKg(valorKg)
                .build();

        var recebimentoLixo = RecebimentoLixo.builder()
                .dataRecebimento(dataRecebimento)
                .perfil(perfil)
                .pessoa(pessoa)
                .pontosColeta(pontosColeta)
                .tiposLixo(tiposLixo)
                .build();

        var matcher = ExampleMatcher.matching()
                .withMatcher("dataRecebimento", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("pessoa.cpf", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("pontosColeta.nome", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("pontosColeta.estado", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("tiposLixo.nome", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<RecebimentoLixo> example = Example.of(recebimentoLixo, matcher);
        var entities = service.findAll(example);
        if (entities.isEmpty()) return ResponseEntity.notFound().build();
        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }
}
