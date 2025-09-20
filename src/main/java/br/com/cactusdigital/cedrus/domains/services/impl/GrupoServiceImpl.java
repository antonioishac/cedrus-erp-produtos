package br.com.cactusdigital.cedrus.domains.services.impl;

import br.com.cactusdigital.cedrus.api.request.GrupoRequest;
import br.com.cactusdigital.cedrus.domains.repositories.GrupoRepository;
import br.com.cactusdigital.cedrus.domains.services.GrupoService;
import br.com.cactusdigital.cedrus.domains.services.dtos.GrupoDTO;
import br.com.cactusdigital.cedrus.domains.services.mappers.grupo.GrupoMapper;
import br.com.cactusdigital.cedrus.domains.services.mappers.grupo.GrupoSalvarMapper;
import br.com.cactusdigital.cedrus.infrastructure.handler.ApiException;
import br.com.cactusdigital.cedrus.infrastructure.handler.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository repository;
    private final GrupoSalvarMapper salvarMapper;
    private final GrupoMapper mapper;

    @Override
    public GrupoDTO salvar(GrupoRequest request) {
        existeGrupo(request.nome());
        var grupo = repository.save(salvarMapper.toEntity(request));
        return mapper.toDto(grupo);
    }

    @Override
    public void existeGrupo(String nome) {
        repository.findByNome(nome).ifPresent(grupo -> {
            throw new ApiException(ExceptionCodeEnum.GRUPO_EXISTENTE);
        });
    }
}
