package br.com.uraniaquest.backend.resources.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para criação e atualização de Categorias da Tecnologia Nuclear")
public class CategoriaDTO {

    @Schema(description = "ID da categoria (gerado automaticamente)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "O título da categoria é obrigatório")
    @Size(min = 3, max = 80, message = "O título deve ter entre 3 e 80 caracteres")
    @Schema(description = "Nome da frente de benefício nuclear", example = "Alimentação")
    private String titulo;

    @NotBlank(message = "O subtítulo é obrigatório")
    @Size(min = 5, max = 150, message = "O subtítulo deve ter entre 5 e 150 caracteres")
    @Schema(description = "Resumo curto exibido no cartão da categoria", example = "Conservação de alimentos e segurança alimentar")
    private String subtitulo;

    @NotBlank(message = "O resumo educativo é obrigatório")
    @Size(min = 20, max = 600, message = "O resumo educativo deve ter entre 20 e 600 caracteres")
    @Schema(description = "Explicação educativa sobre os benefícios da categoria", example = "A irradiação alimentar elimina bactérias e fungos sem tornar o alimento radioativo.")
    private String resumoEducativo;

    @Schema(description = "Emoji ilustrativo da categoria", example = "🍎")
    private String emoji;
}