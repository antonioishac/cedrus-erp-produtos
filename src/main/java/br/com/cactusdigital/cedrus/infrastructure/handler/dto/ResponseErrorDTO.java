package br.com.cactusdigital.cedrus.infrastructure.handler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

@Tag(name = "ResponseErrorDTO")
@Schema(name = "ResponseErrorDTO", description = "Resposta de erro da requisição")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ResponseErrorDTO {

    @Schema(name = "error", description = "Mensagem de erro da requisição")
    private ApiErrorDTO error;

}
