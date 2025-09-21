package br.com.cactusdigital.cedrus.domains.services.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Map;

public class MergeUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    public static <T> void merge(Map<String, Object> dadosOrigem, T destino,
                                 HttpServletRequest request, Class<T> clazz) {

        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            // Converte o Map em objeto do tipo destino
            T origem = objectMapper.convertValue(dadosOrigem, clazz);

            // BeanWrapper para acessar propriedades via getters/setters
            BeanWrapper wrapperDestino = new BeanWrapperImpl(destino);
            BeanWrapper wrapperOrigem = new BeanWrapperImpl(origem);

            dadosOrigem.keySet().forEach(nomePropriedade -> {
                if (wrapperDestino.isWritableProperty(nomePropriedade)) {
                    Object valor = wrapperOrigem.getPropertyValue(nomePropriedade);
                    wrapperDestino.setPropertyValue(nomePropriedade, valor);
                }
            });

        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }
}
