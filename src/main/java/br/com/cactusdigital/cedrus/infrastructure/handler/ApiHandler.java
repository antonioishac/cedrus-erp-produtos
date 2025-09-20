package br.com.cactusdigital.cedrus.infrastructure.handler;

import br.com.cactusdigital.cedrus.infrastructure.MessageConfig;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ApiErrorDTO;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ApiErrorsDTO;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ApiProperties;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ResponseErrorDTO;
import br.com.cactusdigital.cedrus.infrastructure.handler.util.ExceptionCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import java.util.Arrays;
import java.util.List;

public class ApiHandler {

    protected static final String MESSAGE_DEFAULT_EXCEPTION = "Uma Exception foi lancada:";

    protected static final Logger LOG = LoggerFactory.getLogger(ApiHandler.class);

    protected ApiProperties properties;
    protected MessageConfig messageConfig;

    protected ApiErrorsDTO apiErrorDTO(ExceptionCodeEnum exceptionCodeEnum) {

        return ApiErrorsDTO
                .builder()
                .code( exceptionCodeEnum.getCode() )
                .description(this.messageConfig.message(exceptionCodeEnum))
                .build();
    }

    protected ResponseEntity<ResponseErrorDTO> responseEntity(
            final ServletWebRequest request, String version, ApiErrorsDTO message, HttpStatus httpStatus) {

        return responseEntity(request, version, Arrays.asList(message),httpStatus);
    }

    protected ResponseEntity<ResponseErrorDTO> responseEntity(
            final ServletWebRequest request, String version, List<ApiErrorsDTO> messages, HttpStatus httpStatus) {

        ApiErrorDTO message = new ApiErrorDTO(
                version, httpStatus.value(), messages, request.getRequest().getRequestURI(), request.getRequest().getMethod());
        return responseEntity(message);
    }

    protected ResponseEntity<ResponseErrorDTO> responseEntity(ApiErrorDTO messages) {
        LOG.info("{}", messages);
        ResponseErrorDTO body = new ResponseErrorDTO(messages);
        LOG.info("{}", body );
        return ResponseEntity.status(HttpStatus.valueOf(messages.getStatus())).body(body);
    }
}
