package br.com.uraniaquest.backend.resources;

import br.com.uraniaquest.backend.resources.dtos.FlashcardDTO;
import br.com.uraniaquest.backend.services.FlashcardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
@RequiredArgsConstructor
@Tag(name = "2. Flashcards", description = "Endpoints para gestão das perguntas e respostas interativas do UrâniaQuest")
public class FlashcardResource {

    private final FlashcardService service;

    @GetMapping
    @Operation(summary = "Listar todos os flashcards", description = "Retorna todos os flashcards disponíveis para o quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de flashcards retornada com sucesso")
    })
    public ResponseEntity<List<FlashcardDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Procurar flashcard por ID", description = "Retorna um flashcard específico pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flashcard encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Flashcard não encontrado para o ID informado")
    })
    public ResponseEntity<FlashcardDTO> buscarPorId(
            @Parameter(description = "ID do flashcard", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/pesquisa")
    @Operation(summary = "Pesquisar flashcards por texto na pergunta (Query Method)", description = "Procura flashcards que contenham a palavra-chave no enunciado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetro de pesquisa inválido ou ausente")
    })
    public ResponseEntity<List<FlashcardDTO>> buscarPorTexto(
            @Parameter(description = "Palavra-chave (ex: irradiação, radiofármacos, usinas)", example = "irradiação", required = true)
            @RequestParam String termo) {
        return ResponseEntity.ok(service.buscarPorTextoNaPergunta(termo));
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Listar flashcards por Categoria", description = "Retorna todos os flashcards pertencentes a uma categoria específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de flashcards da categoria retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada para o ID informado")
    })
    public ResponseEntity<List<FlashcardDTO>> listarPorCategoria(
            @Parameter(description = "ID da categoria vinculada", example = "1", required = true)
            @PathVariable Long categoriaId) {
        return ResponseEntity.ok(service.listarPorCategoria(categoriaId));
    }

    @PostMapping
    @Operation(summary = "Criar novo flashcard", description = "Regista um novo flashcard de escolha múltipla vinculado a uma categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flashcard criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados"),
            @ApiResponse(responseCode = "404", description = "Categoria informada não encontrada")
    })
    public ResponseEntity<FlashcardDTO> criar(@Valid @RequestBody FlashcardDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar flashcard", description = "Atualiza pergunta, alternativas ou explicação de um flashcard")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flashcard atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados"),
            @ApiResponse(responseCode = "404", description = "Flashcard ou Categoria não encontrados")
    })
    public ResponseEntity<FlashcardDTO> atualizar(
            @Parameter(description = "ID do flashcard a ser atualizado", example = "1", required = true)
            @PathVariable Long id,
            @Valid @RequestBody FlashcardDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover flashcard", description = "Elimina um flashcard pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Flashcard removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Flashcard não encontrado para o ID informado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do flashcard a ser removido", example = "1", required = true)
            @PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}