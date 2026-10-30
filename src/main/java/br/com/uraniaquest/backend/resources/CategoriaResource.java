package br.com.uraniaquest.backend.resources;

import br.com.uraniaquest.backend.resources.dtos.CategoriaDTO;
import br.com.uraniaquest.backend.services.CategoriaService;
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
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "1. Categorias", description = "Endpoints para gestão das frentes de benefícios da tecnologia nuclear")
public class CategoriaResource {

    private final CategoriaService service;

    @GetMapping
    @Operation(summary = "Listar todas as categorias", description = "Retorna todas as categorias educativas registadas no UrâniaQuest")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    })
    public ResponseEntity<List<CategoriaDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Procurar categoria por ID", description = "Retorna os detalhes de uma categoria específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada para o ID informado")
    })
    public ResponseEntity<CategoriaDTO> buscarPorId(
            @Parameter(description = "ID da categoria", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/pesquisa")
    @Operation(summary = "Pesquisar categorias por texto (Query Method)", description = "Procura categorias cujo título ou subtítulo contenham o termo informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetro de pesquisa inválido ou ausente")
    })
    public ResponseEntity<List<CategoriaDTO>> buscarPorTexto(
            @Parameter(description = "Termo de pesquisa (ex: Saúde, Alimentação, Energia)", example = "Saúde", required = true)
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada para o ID informado")
    })
    public ResponseEntity<CategoriaDTO> atualizar(
            @Parameter(description = "ID da categoria a ser atualizada", example = "1", required = true)
            @PathVariable Long id,
            @Valid @RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover categoria", description = "Elimina uma categoria do sistema pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada para o ID informado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da categoria a ser removida", example = "1", required = true)
            @PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}