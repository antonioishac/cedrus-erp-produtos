package br.com.cactusdigital.cedrus.api.controller;

import br.com.cactusdigital.cedrus.api.request.UnidadeRequest;
import br.com.cactusdigital.cedrus.domains.services.UnidadeService;
import br.com.cactusdigital.cedrus.domains.services.dtos.UnidadeDTO;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ResponseErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
