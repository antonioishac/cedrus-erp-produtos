package br.com.cactusdigital.cedrus.api.controller;

import br.com.cactusdigital.cedrus.api.request.UnidadeRequest;
import br.com.cactusdigital.cedrus.api.request.filter.UnidadeFiltro;
import br.com.cactusdigital.cedrus.api.response.PageResponseDTO;
import br.com.cactusdigital.cedrus.domains.services.UnidadeService;
import br.com.cactusdigital.cedrus.domains.services.dtos.UnidadeDTO;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ResponseErrorDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/unidades")
@RequiredArgsConstructor
@Tag(name = "Unidades", description = "Gerenciando unidades.")
public class UnidadeController {

    private final UnidadeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criação de uma nova unidade", description = "Unidades", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseErrorDTO.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseErrorDTO.class)))
    })
    public ResponseEntity<UnidadeDTO> salvarUnidade(@Valid @RequestBody UnidadeRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvar(request));
    }

    @Operation(summary = "Listando unidades.", description = "Unidades",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Unidades list"),
                    @ApiResponse(
                            responseCode = "204",
                            description = "List unidades empty returned",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseErrorDTO.class)))
            })
    @GetMapping()
    public ResponseEntity<PageResponseDTO<UnidadeDTO>> buscarUnidadeFiltro(
            @Parameter(description = "Número inicial da página.", example = "0") Integer page,
            @Parameter(description = "Qtde de páginas.", example = "10") Integer size,
            @ParameterObject UnidadeFiltro filtro) {

        var data = service.buscar(filtro, page, size);

        var response = new PageResponseDTO<>(
                data.getContent(),
                data.getTotalElements(),
                data.getTotalPages(),
                data.getNumber(),
                data.getSize());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obter unidade.", description = "Unidades",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Unidade"),
                    @ApiResponse(
                            responseCode = "204",
                            description = "unidade empty returned",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseErrorDTO.class)))
            })
    @GetMapping("/{unidadeId}")
    public ResponseEntity<UnidadeDTO> buscarUnidade(
            @PathVariable("unidadeId") @Parameter(description = "Id da unidade.", example = "1") Integer unidadeId) {

        var data = service.buscarUnidadePeloId(unidadeId);
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Atualizar unidade", description = "Unidades",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid body field",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseErrorDTO.class)))
            })
    @PutMapping("/{unidadeId}")
    public ResponseEntity<UnidadeDTO> atualizar(
            @Parameter(
                    description = "ID da unidade que será atualizada",
                    example = "1",
                    required = true
            )
            @PathVariable Integer unidadeId,
            @RequestBody UnidadeRequest request) {
        var result = service.atualizar(unidadeId, request);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Atualização parcial de unidade", description = "Unidades",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid body field",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseErrorDTO.class)))
            })
    @PatchMapping("/{unidadeId}")
    public ResponseEntity<UnidadeDTO> atualizarParcial(
            @Parameter(
                    description = "ID da unidade que será atualizada",
                    example = "2",
                    required = true
            )
            @PathVariable Integer unidadeId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UnidadeRequest.class)
                    )
            )
            @RequestBody JsonNode campos,
            HttpServletRequest request) {

        Map<String, Object> camposMap = new ObjectMapper().convertValue(campos, new TypeReference<>() {});
        var result = service.atualizarParcial(unidadeId, camposMap, request);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Remover unidade pelo id", description = "Unidades",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid id field",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseErrorDTO.class)))
            })

    @DeleteMapping("/{unidadeId}")
    public ResponseEntity<Void> removeBank(
            @PathVariable("unidadeId") @Parameter(description = "Id da unidade.", example = "1") Integer unidadeId) {

        service.deletar(unidadeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
