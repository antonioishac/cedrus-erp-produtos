package br.com.cactusdigital.cedrus.infrastructure.handler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(name = "APIError")
@Schema(title = "MessageException", description = "Default response when an exception occurs" )
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ApiErrorDTO {
    private List<ApiErrorsDTO> messages;

    @Schema(name = "status", description = "Code exception", type = "int", example = "400")
    private Integer status;

    @JsonProperty(value = "api-version")
    @Schema(name = "apiVersion", description = "Application Version", example = "1.0.0")
    private String apiVersion;

    @Schema(name = "timestamp", description = "Data e Hora da Ocorrência", type = "long", example = "'2025007011845'")
    private Long timestamp;

    @Schema(name = "endpoint", description = "URI Request", example = "/v1/customer")
    private String endpoint;

    @Schema(name = "method", description = "POST/GET/PUT/DELETE", example = "POST")
    private String method;

    public ApiErrorDTO(final String apiVersion, final Integer status, final ApiErrorsDTO message, final String endpoint, final String method) {
        super();
        this.messages = new ArrayList<>(Arrays.asList(message));
        this.setEndpoint(endpoint);
        this.method = method;
        this.setStatus(status);
        this.timestamp = System.currentTimeMillis();
        this.apiVersion = apiVersion;
    }

    public ApiErrorDTO(final String apiVersion, final Integer status,
                       final List<ApiErrorsDTO> messages, final String endpoint, final String method ) {

        this(apiVersion, status, ApiErrorsDTO.builder().build(), endpoint, method);
        this.messages =  messages;
    }
}
