package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.VoluntarioPerfilRequest;
import br.com.fiap.bluehorizon.dto.response.VoluntarioPerfilResponse;
import br.com.fiap.bluehorizon.entity.TiposLixo;
import br.com.fiap.bluehorizon.entity.VoluntarioPerfil;
import br.com.fiap.bluehorizon.service.VoluntarioPerfilService;
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
@RequestMapping(value = "/voluntario-perfil")
public class VoluntarioPerfilResource implements ResourceDTO<VoluntarioPerfilRequest, VoluntarioPerfilResponse>{

    @Autowired
    private VoluntarioPerfilService service;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<VoluntarioPerfilResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<VoluntarioPerfilResponse> save(@RequestBody @Valid VoluntarioPerfilRequest r) {
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
    public ResponseEntity<Collection<VoluntarioPerfilResponse>> findAll(
            @RequestParam(name = "qntdLixo", required = false) Float qntdLixo

    ){
        var perfil = VoluntarioPerfil.builder()
                .qntdLixo(qntdLixo)
                .build();

        var matcher = ExampleMatcher.matching()
                .withMatcher("qntdLixo", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<VoluntarioPerfil> example = Example.of(perfil, matcher);
        var entities = service.findAll(example);
        if (entities.isEmpty()) return ResponseEntity.notFound().build();
        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }
}
