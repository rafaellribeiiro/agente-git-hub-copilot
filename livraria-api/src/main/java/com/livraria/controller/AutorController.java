package com.livraria.controller;

import com.livraria.entity.Autor;
import com.livraria.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@Tag(name = "Autores", description = "API para gerenciamento de autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Operation(summary = "Listar todos os autores", description = "Retorna uma lista com todos os autores cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de autores recuperada com sucesso")
    @GetMapping
    public List<Autor> listarTodos() {
        return autorService.buscarTodos();
    }

    @Operation(summary = "Buscar autor por ID", description = "Retorna um autor específico baseado no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        return autorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar novo autor", description = "Cria um novo autor com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public Autor criar(@Valid @RequestBody Autor autor) {
        return autorService.salvar(autor);
    }

    @Operation(summary = "Atualizar autor", description = "Atualiza os dados de um autor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable Long id, @Valid @RequestBody Autor autor) {
        return autorService.buscarPorId(id)
                .map(autorExistente -> {
                    autor.setId(id);
                    return ResponseEntity.ok(autorService.salvar(autor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar autor", description = "Remove um autor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!autorService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        autorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
