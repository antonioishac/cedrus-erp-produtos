package br.com.cactusdigital.cedrus.infrastructure.handler;

import br.com.cactusdigital.cedrus.infrastructure.handler.util.ExceptionCodeEnum;
import lombok.Getter;

import java.io.Serial;

public class ApiException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5127042490049753890L;

    @Getter
    private final ExceptionCodeEnum exceptionCodeEnum;

    public ApiException(final ExceptionCodeEnum exceptionCodeEnum, final Throwable e) {
        super(e);
        this.exceptionCodeEnum = exceptionCodeEnum;
    }

    public ApiException(final ExceptionCodeEnum exceptionCodeEnum) {
        this(exceptionCodeEnum, null);
    }

}

