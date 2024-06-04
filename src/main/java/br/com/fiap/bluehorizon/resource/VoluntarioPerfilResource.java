package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.VoluntarioPerfilRequest;
import br.com.fiap.bluehorizon.dto.response.VoluntarioPerfilResponse;
import br.com.fiap.bluehorizon.entity.TiposLixo;
import br.com.fiap.bluehorizon.entity.VoluntarioPerfil;
import br.com.fiap.bluehorizon.service.VoluntarioPerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value = "/voluntario-perfil", produces = {"application/json"})
@Tag(name = "bluehorizon-api")
public class VoluntarioPerfilResource implements ResourceDTO<VoluntarioPerfilRequest, VoluntarioPerfilResponse>{

    @Autowired
    private VoluntarioPerfilService service;

    @Override
    @Operation(summary = "Realiza busca dos perfis de voluntários pelo id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<VoluntarioPerfilResponse> findById(@PathVariable Long id) {
        try {
            var entity = service.findById(id);
            if (entity == null) return ResponseEntity.notFound().build();
            var response = service.toResponse(entity);
            return ResponseEntity.ok(response);
        }  catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }


    }

    @Override
    @Transactional
    @Operation(summary = "Realiza o cadastro de perfis dos voluntários", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para serem cadastrados"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar")
    })
    @PostMapping
    public ResponseEntity<VoluntarioPerfilResponse> save(@RequestBody @Valid VoluntarioPerfilRequest r) {
        try {
            var entity = service.toEntity(r);
            entity = service.save(entity);

            var response = service.toResponse(entity);

            var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(response);
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }


    }

    @Operation(summary = "Realiza busca de todos os perfis e pela quantidade de lixo", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping
    public ResponseEntity<Collection<VoluntarioPerfilResponse>> findAll(
            @RequestParam(name = "qntdLixo", required = false) Float qntdLixo

    ){
        try {
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
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }


    }
}
