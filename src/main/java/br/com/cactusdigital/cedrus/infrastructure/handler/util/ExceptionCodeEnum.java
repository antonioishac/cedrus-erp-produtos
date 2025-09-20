package br.com.cactusdigital.cedrus.infrastructure.handler.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCodeEnum {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    UNIDADE_EXISTENTE(HttpStatus.BAD_REQUEST),
    GRUPO_EXISTENTE(HttpStatus.BAD_REQUEST),
    ERROR_ID_BANK_NOT_FOUND(HttpStatus.BAD_REQUEST);

    private final HttpStatus httpStatus;

    ExceptionCodeEnum(final HttpStatus httpStatus ) {
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return this.name();
    }

}
