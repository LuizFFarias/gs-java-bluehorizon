package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.TiposLixoRequest;
import br.com.fiap.bluehorizon.dto.response.TiposLixoResponse;
import br.com.fiap.bluehorizon.entity.TiposLixo;
import br.com.fiap.bluehorizon.service.TiposLixoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Collection;

@RestController
@RequestMapping(value = "/tipos-lixo")
public class TiposLixoResource implements ResourceDTO<TiposLixoRequest, TiposLixoResponse>{

    @Autowired
    private TiposLixoService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<TiposLixoResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @Transactional
    public ResponseEntity<TiposLixoResponse> save(@RequestBody @Valid TiposLixoRequest r) {
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
    public ResponseEntity<Collection<TiposLixoResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "valorKg", required = false)BigDecimal valorKg
            ) {

        var tiposLixo = TiposLixo.builder()
                .nome(nome)
                .valorKg(valorKg)
                .build();

        var matcher = ExampleMatcher.matching()
                .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("valorKg", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<TiposLixo> example = Example.of(tiposLixo, matcher);
        var entities = service.findAll(example);
        if (entities.isEmpty()) return ResponseEntity.notFound().build();
        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }
}
