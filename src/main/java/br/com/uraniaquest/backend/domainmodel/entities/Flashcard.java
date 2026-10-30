package br.com.uraniaquest.backend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_UQ_FLASHCARD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flashcard")
    private Long id;

    @Column(name = "ds_pergunta", nullable = false, length = 300)
    private String pergunta;

    @Column(name = "ds_opcao_a", nullable = false, length = 200)
    private String opcaoA;

    @Column(name = "ds_opcao_b", nullable = false, length = 200)
    private String opcaoB;

    @Column(name = "ds_opcao_c", nullable = false, length = 200)
    private String opcaoC;

    @Column(name = "nr_indice_correto", nullable = false)
    private Integer indiceCorreto;

    @Column(name = "ds_explicacao", nullable = false, length = 500)
    private String explicacao;

    @Column(name = "vl_moedas_recompensa", nullable = false)
    private Integer moedasRecompensa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;
}