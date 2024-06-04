package br.com.fiap.bluehorizon.resource;

import br.com.fiap.bluehorizon.dto.request.VoluntarioPessoaRequest;
import br.com.fiap.bluehorizon.dto.response.VoluntarioPessoaResponse;
import br.com.fiap.bluehorizon.entity.VoluntarioEndereco;
import br.com.fiap.bluehorizon.entity.VoluntarioPerfil;
import br.com.fiap.bluehorizon.entity.VoluntarioPessoa;
import br.com.fiap.bluehorizon.service.VoluntarioPessoaService;
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

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping(value = "/voluntario-pessoa", produces = {"application/json"})
@Tag(name = "bluehorizon-api")
public class VoluntarioPessoaResource implements ResourceDTO<VoluntarioPessoaRequest, VoluntarioPessoaResponse>{

    @Autowired
    private VoluntarioPessoaService service;

    @Override
    @Operation(summary = "Realiza busca dos dados das pessoas pelo id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<VoluntarioPessoaResponse> findById(@PathVariable Long id) {

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
    @Operation(summary = "Realiza o cadastro de novas pessoas voluntárias", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para serem cadastrados"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar")
    })
    @PostMapping
    public ResponseEntity<VoluntarioPessoaResponse> save(@RequestBody @Valid VoluntarioPessoaRequest r) {
        try {
            if (LocalDate.now().getYear() - r.dtNascimento().getYear() < 18 && LocalDate.now().getMonthValue() >= r.dtNascimento().getMonthValue()
                    && LocalDate.now().getDayOfMonth() > r.dtNascimento().getDayOfMonth()){
                return ResponseEntity.badRequest().build();
            }

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

    @Operation(summary = "Realiza busca todas as pessoas registradas e pelo CPF, nome, CEP e quantidade de lixo", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar dados")
    })
    @GetMapping
    public ResponseEntity<Collection<VoluntarioPessoaResponse>> findAll(
            @RequestParam(name = "cpf", required = false) String cpf,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "dtNascimento", required = false) LocalDate dtNascimento,
            @RequestParam(name = "endereco.cep", required = false) String cep,
            @RequestParam(name = "endereco.rua", required = false) String rua,
            @RequestParam(name = "endereco.numero", required = false) String numero,
            @RequestParam(name = "perfil.qntdLixo", required = false) Float qntdLixo
            ){

        try {
            var endereco = VoluntarioEndereco.builder()
                    .cep(cep)
                    .rua(rua)
                    .numero(numero)
                    .build();

            var perfil = VoluntarioPerfil.builder()
                    .qntdLixo(qntdLixo)
                    .build();

            var pessoa = VoluntarioPessoa.builder()
                    .cpf(cpf)
                    .nome(nome)
                    .dtNascimento(dtNascimento)
                    .endereco(endereco)
                    .perfil(perfil)
                    .build();

            var matcher = ExampleMatcher.matching()
                    .withMatcher("cpf", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("endereco.cep", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("perfil.qntdLixo", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withIgnoreCase()
                    .withIgnoreNullValues();

            Example<VoluntarioPessoa> example = Example.of(pessoa, matcher);
            var entities = service.findAll(example);
            if (entities.isEmpty()) return ResponseEntity.notFound().build();
            var response = entities.stream().map(service::toResponse).toList();

            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        }


}
