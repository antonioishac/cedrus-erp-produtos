package br.com.cactusdigital.cedrus.domains.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "PRODUTO_SUBGRUPO")
@Entity
public class SubgrupoEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO_GRUPO")
    private GrupoEntity grupo;

    @Column(name = "NOME", length = 100)
    private String nome;

    @Column(name = "DESCRICAO", length = 250)
    private String descricao;
}
