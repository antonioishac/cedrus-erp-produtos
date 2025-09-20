package br.com.cactusdigital.cedrus.domains.services.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class GrupoDTO {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private String descricao;
}
