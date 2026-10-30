package br.com.uraniaquest.backend.resources;

import br.com.uraniaquest.backend.resources.dtos.FlashcardDTO;
import br.com.uraniaquest.backend.services.FlashcardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    public ResponseEntity<List<FlashcardDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Procurar flashcard por ID", description = "Retorna um flashcard específico pelo seu identificador")
    public ResponseEntity<FlashcardDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/pesquisa")
    @Operation(summary = "Pesquisar flashcards por texto na pergunta (Query Method)", description = "Procura flashcards que contenham a palavra-chave no enunciado")
    public ResponseEntity<List<FlashcardDTO>> buscarPorTexto(
            @Parameter(description = "Palavra-chave (ex: irradiação, radiofármacos, usinas)", required = true)
            @RequestParam String termo) {
        return ResponseEntity.ok(service.buscarPorTextoNaPergunta(termo));
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Listar flashcards por Categoria", description = "Retorna todos os flashcards pertencentes a uma categoria específica")
    public ResponseEntity<List<FlashcardDTO>> listarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(service.listarPorCategoria(categoriaId));
    }

    @PostMapping
    @Operation(summary = "Criar novo flashcard", description = "Regista um novo flashcard de escolha múltipla vinculado a uma categoria")
    public ResponseEntity<FlashcardDTO> criar(@Valid @RequestBody FlashcardDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar flashcard", description = "Atualiza pergunta, alternativas ou explicação de um flashcard")
    public ResponseEntity<FlashcardDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FlashcardDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover flashcard", description = "Elimina um flashcard pelo seu ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}