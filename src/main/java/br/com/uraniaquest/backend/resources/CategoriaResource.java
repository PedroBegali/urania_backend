package br.com.uraniaquest.backend.resources;

import br.com.uraniaquest.backend.resources.dtos.CategoriaDTO;
import br.com.uraniaquest.backend.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "1. Categorias", description = "Endpoints para gestão das frentes de benefícios da tecnologia nuclear")
public class CategoriaResource {

    private final CategoriaService service;

    @GetMapping
    @Operation(summary = "Listar todas as categorias", description = "Retorna todas as categorias educativas registadas no UrâniaQuest")
    public ResponseEntity<List<CategoriaDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Procurar categoria por ID", description = "Retorna os detalhes de uma categoria específica")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/pesquisa")
    @Operation(summary = "Pesquisar categorias por texto (Query Method)", description = "Procura categorias cujo título ou subtítulo contenham o termo informado")
    public ResponseEntity<List<CategoriaDTO>> buscarPorTexto(
            @Parameter(description = "Termo de pesquisa (ex: Saúde, Alimentação, Energia)", required = true)
            @RequestParam String termo) {
        return ResponseEntity.ok(service.buscarPorTexto(termo));
    }

    @PostMapping
    @Operation(summary = "Registar nova categoria", description = "Cria uma nova frente temática validando os dados com Spring Validation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    public ResponseEntity<CategoriaDTO> criar(@Valid @RequestBody CategoriaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria existente", description = "Atualiza os dados de uma categoria pelo seu ID")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover categoria", description = "Elimina uma categoria do sistema pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}