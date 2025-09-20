package br.com.cactusdigital.cedrus.api.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UnidadeRequest(
        @Schema(description = "Sigla da unidade de medida", example = "KG")
        String sigla,

        @Schema(description = "Descrição da unidade de medida", example = "Quilograma")
        String descricao,

        @Schema(
                description = "Indica se pode ser fracionada (S = Sim, N = Não)",
                example = "S",
                allowableValues = {"S", "N"}
        )
        String podeFracionar) {
}
