package br.com.cactusdigital.cedrus.api.request.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springdoc.core.annotations.ParameterObject;

@ParameterObject
public record UnidadeFiltro(
        @Schema(description = "Sigla da unidade de medida", defaultValue = "")
        String sigla,

        @Schema(description = "Descrição da unidade de medida", defaultValue = "")
        String descricao,

        @Schema(description = "Pode fracionar a unidade de medida", defaultValue = "")
        String podeFracionar) {
}
