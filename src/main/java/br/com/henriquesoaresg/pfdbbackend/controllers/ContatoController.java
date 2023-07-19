package br.com.henriquesoaresg.pfdbbackend.controllers;

import br.com.henriquesoaresg.pfdbbackend.models.dtos.ContatoUpdateDto;
import br.com.henriquesoaresg.pfdbbackend.models.entities.Contato;
import br.com.henriquesoaresg.pfdbbackend.services.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contatos")
@Tag(name = "Contatos")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @PutMapping(value = "/id/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Atualizará um contato através de um id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Atributos passados no corpo da requisição incorretos"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<Contato> updateById(@PathVariable("id") Long id, @RequestBody @Valid ContatoUpdateDto body){
        return new ResponseEntity<>(this.contatoService.updateById(id, body), HttpStatus.OK);
    }
}
