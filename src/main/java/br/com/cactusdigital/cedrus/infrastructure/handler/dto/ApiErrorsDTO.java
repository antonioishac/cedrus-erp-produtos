package br.com.cactusdigital.cedrus.infrastructure.handler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@Schema(name = "Api Error", description = "Contém o código e descrição do erro")
public class ApiErrorsDTO {

    @Schema(name = "code", description = "Código do erro.", example = "400")
    private String code;

    @Schema(name = "description", description = "Descrição do erro.", example = "O campo nome é obrigatório.")
    private String description;
}
