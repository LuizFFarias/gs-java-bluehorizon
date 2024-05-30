package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.PontosColetaRequest;
import br.com.fiap.bluehorizon.dto.response.PontosColetaResponse;
import br.com.fiap.bluehorizon.entity.PontosColeta;
import br.com.fiap.bluehorizon.service.PontosColetaService;
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
@RequestMapping(value = "/pontos-coleta")
public class PontosColetaResource implements ResourceDTO<PontosColetaRequest, PontosColetaResponse>{

    @Autowired
    private PontosColetaService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<PontosColetaResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<PontosColetaResponse> save(@RequestBody @Valid PontosColetaRequest r) {
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
    public ResponseEntity<Collection<PontosColetaResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "estado",  required = false) String estado,
            @RequestParam(name = "gerente", required = false) String gerente

    ){
        var coleta = PontosColeta.builder()
                .nome(nome)
                .estado(estado)
                .gerente(gerente)
                .build();


        var matcher = ExampleMatcher.matching()
                .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("estado", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("gerente", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<PontosColeta> example = Example.of(coleta, matcher);
        var entities = service.findAll(example);
        if (entities.isEmpty()) return ResponseEntity.notFound().build();
        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    /*
    TODO: fazer update no gerente do ponto
     */
}
