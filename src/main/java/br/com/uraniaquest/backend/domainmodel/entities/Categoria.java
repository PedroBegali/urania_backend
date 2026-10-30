package br.com.uraniaquest.backend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_UQ_CATEGORIA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;

    @Column(name = "nm_titulo", nullable = false, length = 80)
    private String titulo;

    @Column(name = "ds_subtitulo", nullable = false, length = 150)
    private String subtitulo;

    @Column(name = "ds_resumo_educativo", nullable = false, length = 600)
    private String resumoEducativo;

    @Column(name = "ds_icone_emoji", length = 20)
    private String emoji;
}