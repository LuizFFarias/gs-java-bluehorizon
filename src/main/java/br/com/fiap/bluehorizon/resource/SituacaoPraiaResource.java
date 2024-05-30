package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.SituacaoPraiaRequest;
import br.com.fiap.bluehorizon.dto.response.SituacaoPraiaResponse;
import br.com.fiap.bluehorizon.entity.SituacaoPraia;
import br.com.fiap.bluehorizon.service.SituacaoPraiaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping(value = "/situacao-praia")
public class SituacaoPraiaResource implements ResourceDTO<SituacaoPraiaRequest, SituacaoPraiaResponse>{

    @Autowired
    private SituacaoPraiaService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<SituacaoPraiaResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<SituacaoPraiaResponse> save(@RequestBody @Valid SituacaoPraiaRequest r) {
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
    public ResponseEntity<Collection<SituacaoPraiaResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "cidade", required = false) String cidade,
            @RequestParam(name = "nivelSujeira", required = false) Integer nivelSujeira
    ){
        var situacaoPraia = SituacaoPraia.builder()
                .nome(nome)
                .cidade(cidade)
                .nivelSujeira(nivelSujeira)
                .build();

        var matcher = ExampleMatcher.matching()
                .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("cidade", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("nivelSujeira", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<SituacaoPraia> example = Example.of(situacaoPraia, matcher);
        var entities = service.findAll(example);
        if (entities.isEmpty()) return ResponseEntity.notFound().build();
        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }
}
