package br.com.cactusdigital.cedrus.domains.services.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UnidadeDTO {

    @EqualsAndHashCode.Include
    @Schema(description = "Código identificador da unidade")
    private Integer id;

    @Schema(description = "Sigla da unidade de medida", example = "KG")
    private String sigla;

    @Schema(description = "Descrição da unidade de medida", example = "Quilograma")
    private String descricao;

    @Schema(
            description = "Indica se pode ser fracionada (S = Sim, N = Não)",
            example = "S",
            allowableValues = {"S", "N"}
    )
    private String podeFracionar;

}
