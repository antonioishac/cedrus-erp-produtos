package br.com.cactusdigital.cedrus.domains.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TRIBUT_GRUPO_TRIBUTARIO")
@Entity
public class GrupoTributarioEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRICAO", length = 100)
    private String descricao;

    @Column(name = "ORIGEM_MERCADORIA", length = 250)
    private String origemMercadoria;

    @Lob
    @Column(name = "OBSERVACAO")
    private String observacao;
}
