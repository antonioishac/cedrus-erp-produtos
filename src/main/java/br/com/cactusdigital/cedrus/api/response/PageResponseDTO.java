package br.com.cactusdigital.cedrus.api.response;

import java.util.List;

public record PageResponseDTO<T>(List<T> content, long totalElements, int totalPages, int page, int size) {}

