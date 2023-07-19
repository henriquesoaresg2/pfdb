package br.com.henriquesoaresg.pfdbbackend.controllers;

import br.com.henriquesoaresg.pfdbbackend.models.dtos.PessoaUpdateDto;
import br.com.henriquesoaresg.pfdbbackend.models.entities.Pessoa;
import br.com.henriquesoaresg.pfdbbackend.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Método que realizará a criação de uma pessoa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Atributos passados no corpo da requisição incorretos")
    })
    public ResponseEntity<Pessoa> create(@RequestBody @Valid Pessoa pessoa){
        return new ResponseEntity<>(this.pessoaService.create(pessoa), HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Gerará uma lista de todas as pessoas cadastradas no banco de dados, com paginação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pessoas gerada com sucesso")
    })
    public ResponseEntity<Page<Pessoa>> readAll(Pageable pageable){
        return ResponseEntity.ok(this.pessoaService.readAll(pageable));
    }

    @GetMapping(value = "/id/{id}", produces = "application/json")
    @Operation(summary = "Encontrará uma pessoa através de um id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<Pessoa> readById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.pessoaService.readOneById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/id/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Atualizará uma pessoa através de um id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Atributos passados no corpo da requisição incorretos"),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<Pessoa> updateById(@PathVariable("id") Long id, @RequestBody @Valid PessoaUpdateDto body){
        return new ResponseEntity<>(this.pessoaService.updateById(id, body), HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    @Operation(summary = "Deletará uma pessoa através de um id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "203", description = "Pessoa deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        this.pessoaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
