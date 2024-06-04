package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.PontosColetaRequest;
import br.com.fiap.bluehorizon.dto.response.PontosColetaResponse;
import br.com.fiap.bluehorizon.entity.PontosColeta;
import br.com.fiap.bluehorizon.service.PontosColetaService;
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
@RequestMapping(value = "/pontos-coleta", produces = {"application/json"})
@Tag(name = "bluehorizon-api")
public class PontosColetaResource implements ResourceDTO<PontosColetaRequest, PontosColetaResponse>{

    @Autowired
    private PontosColetaService service;

    @Override
    @Operation(summary = "Realiza busca dados dos pontos de coleta pelo id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<PontosColetaResponse> findById(@PathVariable Long id) {
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
    @Transactional
    @Operation(summary = "Realiza o cadastro de novos pontos de coleta", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para serem cadastrados"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar")
    })
    @PostMapping
    public ResponseEntity<PontosColetaResponse> save(@RequestBody @Valid PontosColetaRequest r) {
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

    @Operation(summary = "Realiza busca de todos os pontos de coleta registrados e pelo nome, estado e gerente", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping
    public ResponseEntity<Collection<PontosColetaResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "estado",  required = false) String estado,
            @RequestParam(name = "gerente", required = false) String gerente

    ){
        try {
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
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
