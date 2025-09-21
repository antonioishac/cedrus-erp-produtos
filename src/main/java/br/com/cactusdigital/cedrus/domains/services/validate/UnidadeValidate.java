package br.com.cactusdigital.cedrus.domains.services.validate;

import br.com.cactusdigital.cedrus.domains.repositories.UnidadeRepository;
import br.com.cactusdigital.cedrus.infrastructure.handler.ApiException;
import br.com.cactusdigital.cedrus.infrastructure.handler.util.ExceptionCodeEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnidadeValidate {

    public static void validarUnidadeAntesUpdate(String sigla, Integer id, UnidadeRepository repository) {
        Optional.ofNullable(sigla)
                .filter(s -> !s.isBlank())
                .ifPresent(s -> {
                    if (repository.existsBySiglaIgnoreCaseAndIdNot(s, id)) {
                        throw new ApiException(ExceptionCodeEnum.UNIDADE_EXISTENTE);
                    }
                });
    }
}
