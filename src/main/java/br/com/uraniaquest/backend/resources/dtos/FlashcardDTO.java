package br.com.uraniaquest.backend.resources.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para criação e leitura de Flashcards de perguntas do UrâniaQuest")
public class FlashcardDTO {

    @Schema(description = "ID do flashcard", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "A pergunta do flashcard é obrigatória")
    @Size(min = 10, max = 300, message = "A pergunta deve ter entre 10 e 300 caracteres")
    @Schema(description = "Afirmação ou pergunta exibida no cartão", example = "Os alimentos tratados por irradiação ficam radioativos.")
    private String pergunta;

    @NotBlank(message = "A opção A é obrigatória")
    @Schema(description = "Primeira opção de resposta (índice 0)", example = "Verdadeiro")
    private String opcaoA;

    @NotBlank(message = "A opção B é obrigatória")
    @Schema(description = "Segunda opção de resposta (índice 1)", example = "Falso")
    private String opcaoB;

    @NotBlank(message = "A opção C é obrigatória")
    @Schema(description = "Terceira opção de resposta (índice 2)", example = "Depende do contexto")
    private String opcaoC;

    @NotNull(message = "O índice da alternativa correta é obrigatório")
    @Min(value = 0, message = "O índice correto deve ser 0 (A), 1 (B) ou 2 (C)")
    @Max(value = 2, message = "O índice correto deve ser 0 (A), 1 (B) ou 2 (C)")
    @Schema(description = "Índice da opção correta (0 = A, 1 = B, 2 = C)", example = "1")
    private Integer indiceCorreto;

    @NotBlank(message = "A explicação científica da resposta é obrigatória")
    @Size(min = 15, max = 500, message = "A explicação deve ter entre 15 e 500 caracteres")
    @Schema(description = "Explicação exibida após o jogador responder", example = "Falso! O alimento recebe apenas ondas de energia, sem reter radioatividade.")
    private String explicacao;

    @NotNull(message = "O valor de moedas de recompensa é obrigatório")
    @Min(value = 10, message = "A recompensa mínima é de 10 moedas")
    @Schema(description = "Moedas ganhas ao acertar o flashcard", example = "50")
    private Integer moedasRecompensa;

    @NotNull(message = "O ID da categoria vinculada é obrigatório")
    @Schema(description = "ID da Categoria à qual o flashcard pertence", example = "1")
    private Long categoriaId;

    @Schema(description = "Nome da categoria (apenas leitura)", example = "Alimentação", accessMode = Schema.AccessMode.READ_ONLY)
    private String categoriaTitulo;
}