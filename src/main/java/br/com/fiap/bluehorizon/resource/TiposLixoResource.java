package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.TiposLixoRequest;
import br.com.fiap.bluehorizon.dto.response.TiposLixoResponse;
import br.com.fiap.bluehorizon.entity.TiposLixo;
import br.com.fiap.bluehorizon.service.TiposLixoService;
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

import java.math.BigDecimal;
import java.util.Collection;

@RestController
@RequestMapping(value = "/tipos-lixo", produces = {"application/json"})
@Tag(name = "bluehorizon-api")
public class TiposLixoResource implements ResourceDTO<TiposLixoRequest, TiposLixoResponse>{

    @Autowired
    private TiposLixoService service;

    @Override
    @Operation(summary = "Realiza busca dos tipos de lixo pelo id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<TiposLixoResponse> findById(@PathVariable Long id) {
        try {
            var entity = service.findById(id);
            if (entity == null) return ResponseEntity.notFound().build();
            var response = service.toResponse(entity);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }


    }

    @Override
    @PostMapping
    @Operation(summary = "Realiza o cadastro de novos tipos de lixo", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para serem cadastrados"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar")
    })
    @Transactional
    public ResponseEntity<TiposLixoResponse> save(@RequestBody @Valid TiposLixoRequest r) {
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

    @Operation(summary = "Realiza busca de todos os tipos de lixo e pelo nome e valor do kg", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping
    public ResponseEntity<Collection<TiposLixoResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "valorKg", required = false)BigDecimal valorKg
            ) {

        try {
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
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }


    }
}
