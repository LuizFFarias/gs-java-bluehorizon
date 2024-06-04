package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.VoluntarioEnderecoRequest;
import br.com.fiap.bluehorizon.dto.response.VoluntarioEnderecoResponse;
import br.com.fiap.bluehorizon.entity.VoluntarioEndereco;
import br.com.fiap.bluehorizon.service.VoluntarioEnderecoService;
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
@RequestMapping(value = "/voluntarios-endereco", produces = {"application/json"})
@Tag(name = "bluehorizon-api")
public class VoluntarioEnderecoResource implements ResourceDTO<VoluntarioEnderecoRequest, VoluntarioEnderecoResponse> {

    @Autowired
    private VoluntarioEnderecoService service;


    @Override
    @Operation(summary = "Realiza busca dos endereços dos voluntários pelo id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<VoluntarioEnderecoResponse> findById(@PathVariable Long id) {
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
    @Operation(summary = "Realiza o cadastro de novos endereços", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço salvo com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para serem cadastrarados"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar")

    })
    @PostMapping
    @Transactional
    public ResponseEntity<VoluntarioEnderecoResponse> save(@RequestBody @Valid VoluntarioEnderecoRequest r) {
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

    @Operation(summary = "Realiza busca de todos os endereços e busca por cep, bairro, estado e pais", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping
    public ResponseEntity<Collection<VoluntarioEnderecoResponse>> findAll(
            @RequestParam(name = "cep", required = false) String cep,
            @RequestParam(name = "rua", required = false) String rua,
            @RequestParam(name = "numero", required = false) String numero,
            @RequestParam(name = "bairro", required = false) String bairro,
            @RequestParam(name = "cidade", required = false) String cidade,
            @RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "pais", required = false) String pais

    ) {
        var endereco = VoluntarioEndereco.builder()
                .cep(cep)
                .rua(rua)
                .numero(numero)
                .bairro(bairro)
                .cidade(cidade)
                .estado(estado)
                .pais(pais)
                .build();

        var matcher = ExampleMatcher.matching()
                .withMatcher("cep", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("bairro", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("estado", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("pais", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<VoluntarioEndereco> example = Example.of(endereco, matcher);
        var entities = service.findAll(example);
        if (entities.isEmpty()) return ResponseEntity.notFound().build();
        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }
}
