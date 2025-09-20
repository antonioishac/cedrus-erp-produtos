package br.com.cactusdigital.cedrus.infrastructure.handler;

import br.com.cactusdigital.cedrus.infrastructure.MessageConfig;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ApiErrorsDTO;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ApiProperties;
import br.com.cactusdigital.cedrus.infrastructure.handler.dto.ResponseErrorDTO;
import br.com.cactusdigital.cedrus.infrastructure.handler.util.ConstraintEnum;
import org.hibernate.exception.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionCustomHandler extends ApiHandler {

    @Autowired
    public ApiExceptionCustomHandler(MessageConfig messageConfig, ApiProperties properties){
        this.messageConfig = messageConfig;
        this.properties = properties;
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseErrorDTO> handleAPIException(final ApiException ex, final ServletWebRequest request) {
        LOG.error(MESSAGE_DEFAULT_EXCEPTION, ex );

        return responseEntity(
                request,
                properties.getVersion(),
                apiErrorDTO(ex.getExceptionCodeEnum()),
                ex.getExceptionCodeEnum().getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {

        LOG.error(MESSAGE_DEFAULT_EXCEPTION, ex );

        List<ApiErrorsDTO> messages = new ArrayList<>();

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fes = bindingResult.getFieldErrors();

        for (FieldError fe : fes) {
            String defaultMessage = fe.getDefaultMessage();
            if (StringUtils.isNotBlank(defaultMessage)) {
                String message = messageConfig.message(defaultMessage);
                messages.add(
                        ApiErrorsDTO
                                .builder()
                                .code(defaultMessage)
                                //.description(String.format("%s - %s", fe.getField(), message))
                                .description(String.format(message))
                                .build()
                );
            }
        }
        return responseEntity((ServletWebRequest)request, properties.getVersion(),messages, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseErrorDTO> handleMethodArgumentNotValid(ConstraintViolationException ex, WebRequest request) {
        LOG.error(MESSAGE_DEFAULT_EXCEPTION, ex );
        ConstraintEnum constraint = ConstraintEnum.getConstraint(ex.getConstraintName());
        String msg = messageConfig.message(constraint.getName());
        ApiErrorsDTO apiErrorDTO = ApiErrorsDTO
                .builder()
                .code(constraint.getName())
                .description(msg)
                .build();
        return responseEntity((ServletWebRequest)request, properties.getVersion(),apiErrorDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

