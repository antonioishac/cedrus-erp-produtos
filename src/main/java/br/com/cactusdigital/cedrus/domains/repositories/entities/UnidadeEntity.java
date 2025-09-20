package br.com.cactusdigital.cedrus.domains.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PRODUTO_UNIDADE")
public class UnidadeEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SIGLA", length = 10)
    private String sigla;

    @Column(name = "DESCRICAO", length = 250)
    private String descricao;

    @Column(name = "PODE_FRACIONAR", length = 1)
    private String podeFracionar;
}
