package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.SituacaoPraiaRequest;
import br.com.fiap.bluehorizon.dto.response.SituacaoPraiaResponse;
import br.com.fiap.bluehorizon.entity.SituacaoPraia;
import br.com.fiap.bluehorizon.service.SituacaoPraiaService;
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
@RequestMapping(value = "/situacao-praia", produces = {"application/json"})
@Tag(name = "bluehorizon-api")
public class SituacaoPraiaResource implements ResourceDTO<SituacaoPraiaRequest, SituacaoPraiaResponse>{

    @Autowired
    private SituacaoPraiaService service;

    @Override
    @Operation(summary = "Realiza busca das situações das praias pelo id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<SituacaoPraiaResponse> findById(@PathVariable Long id) {
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
    @Operation(summary = "Realiza o cadastro de novas praias com suas situações", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para serem cadastrados"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar")
    })
    @PostMapping
    public ResponseEntity<SituacaoPraiaResponse> save(@RequestBody @Valid SituacaoPraiaRequest r) {

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

    @Operation(summary = "Realiza busca de todas as praias e pelo nome, cidade e nivel de sujeira", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping
    public ResponseEntity<Collection<SituacaoPraiaResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "cidade", required = false) String cidade,
            @RequestParam(name = "nivelSujeira", required = false) Integer nivelSujeira
    ){
        try {
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
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }


    }
}
