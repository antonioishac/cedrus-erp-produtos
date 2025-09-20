package br.com.cactusdigital.cedrus.infrastructure.handler;

import br.com.cactusdigital.cedrus.infrastructure.MessageConfig;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ApiErrorsDTO;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ApiProperties;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ResponseErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ApiExceptionHandler extends ApiHandler {

    @Autowired
    public ApiExceptionHandler(MessageConfig messageConfig, ApiProperties properties){
        this.messageConfig = messageConfig;
        this.properties = properties;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDTO> handleException(final Exception ex, final ServletWebRequest request) {
        LOG.error(MESSAGE_DEFAULT_EXCEPTION, ex );
        ApiErrorsDTO error = ApiErrorsDTO
                .builder()
                .code("999")
                .description(ex.getMessage())
                .build();
        return responseEntity(request, properties.getVersion(), error, HttpStatus.BAD_REQUEST);
    }
}
