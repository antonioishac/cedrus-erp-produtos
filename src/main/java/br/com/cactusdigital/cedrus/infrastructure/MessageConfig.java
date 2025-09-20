package br.com.cactusdigital.cedrus.infrastructure;

import br.com.cactusdigital.cedrus.infrastructure.handler.util.ExceptionCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageConfig {

    @Autowired
    private MessageSource messageSource;

    public String message(ExceptionCodeEnum message) {
        return message(message.getCode());
    }

    public String message(ExceptionCodeEnum message , Object... args ) {
        return message(message.getCode(), args);
    }

    public String message(String message) {
        return messageSource.getMessage(message, null, message, Locale.getDefault());
    }

    public String message(String message, Object... args ) {
        return messageSource.getMessage(message, args, message, Locale.getDefault());
    }
}

