package br.com.cactusdigital.cedrus.domains.services.impl;

import br.com.cactusdigital.cedrus.api.request.UnidadeRequest;
import br.com.cactusdigital.cedrus.domains.repositories.UnidadeRepository;
import br.com.cactusdigital.cedrus.domains.services.UnidadeService;
import br.com.cactusdigital.cedrus.domains.services.dtos.UnidadeDTO;
import br.com.cactusdigital.cedrus.domains.services.mappers.unidade.UnidadeMapper;
import br.com.cactusdigital.cedrus.domains.services.mappers.unidade.UnidadeSalvarMapper;
import br.com.cactusdigital.cedrus.infrastructure.handler.ApiException;
import br.com.cactusdigital.cedrus.infrastructure.handler.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnidadeServiceImpl implements UnidadeService {

    private final UnidadeRepository repository;
    private final UnidadeMapper mapper;
    private final UnidadeSalvarMapper salvarMapper;

    @Override
    public UnidadeDTO salvar(UnidadeRequest request) {
        verificaSeExiste(request.sigla());

        var unidadeEntity = salvarMapper.toEntity(request);
        unidadeEntity = repository.save(unidadeEntity);
        return mapper.toDto(unidadeEntity);
    }

    @Override
    public void verificaSeExiste(String sigla) {
        repository.findBySigla(sigla).ifPresent(unidade -> {
            throw new ApiException(ExceptionCodeEnum.UNIDADE_EXISTENTE);
        });
    }
}
