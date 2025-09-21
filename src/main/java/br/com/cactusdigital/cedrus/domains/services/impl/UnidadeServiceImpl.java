package br.com.cactusdigital.cedrus.domains.services.impl;

import br.com.cactusdigital.cedrus.api.request.UnidadeRequest;
import br.com.cactusdigital.cedrus.api.request.filter.UnidadeFiltro;
import br.com.cactusdigital.cedrus.domains.repositories.UnidadeRepository;
import br.com.cactusdigital.cedrus.domains.repositories.entities.UnidadeEntity;
import br.com.cactusdigital.cedrus.domains.repositories.specification.UnidadeSpecification;
import br.com.cactusdigital.cedrus.domains.services.UnidadeService;
import br.com.cactusdigital.cedrus.domains.services.dtos.UnidadeDTO;
import br.com.cactusdigital.cedrus.domains.services.mappers.unidade.UnidadeMapper;
import br.com.cactusdigital.cedrus.domains.services.mappers.unidade.UnidadeSalvarMapper;
import br.com.cactusdigital.cedrus.domains.services.utils.MergeUtil;
import br.com.cactusdigital.cedrus.infrastructure.handler.ApiException;
import br.com.cactusdigital.cedrus.infrastructure.handler.util.ExceptionCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

import static br.com.cactusdigital.cedrus.domains.services.validate.UnidadeValidate.validarUnidadeAntesUpdate;

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

    @Override
    public UnidadeEntity existeUnidade(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ApiException(ExceptionCodeEnum.UNIDADE_NAO_EXISTENTE));
    }

    @Override
    public void deletar(Integer id) {
        var result = existeUnidade(id);
        repository.delete(result);
    }

    @Override
    public Page<UnidadeDTO> buscar(UnidadeFiltro filtro, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("descricao").ascending());
        Page<UnidadeEntity> entities = repository.findAll(UnidadeSpecification.filtrar(filtro), pageable);
        return entities.map(mapper::toDto);
    }

    @Override
    public UnidadeDTO atualizar(Integer id, UnidadeRequest request) {
        var unidadeAtual = existeUnidade(id);
        validarUnidadeAntesUpdate(request.sigla(), unidadeAtual.getId(), repository);

        BeanUtils.copyProperties(request, unidadeAtual, "id");
        repository.save(unidadeAtual);
        return mapper.toDto(unidadeAtual);
    }

    @Override
    public UnidadeDTO atualizarParcial(Integer id, Map<String, Object> campos, HttpServletRequest request) {
        var unidadeAtual = existeUnidade(id);
        validarUnidadeAntesUpdate((String) campos.get("sigla"), unidadeAtual.getId(), repository);

        MergeUtil.merge(campos, unidadeAtual, request, UnidadeEntity.class);
        repository.save(unidadeAtual);
        return mapper.toDto(unidadeAtual);
    }
}
