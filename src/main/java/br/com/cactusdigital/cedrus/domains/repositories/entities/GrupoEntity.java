package br.com.cactusdigital.cedrus.domains.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "PRODUTO_GRUPO")
@Entity
public class GrupoEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOME", length = 100)
    private String nome;

    @Column(name = "DESCRICAO", length = 250)
    private String descricao;
}
