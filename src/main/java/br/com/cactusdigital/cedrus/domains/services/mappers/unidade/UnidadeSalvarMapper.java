package br.com.cactusdigital.cedrus.domains.services.mappers.unidade;

import br.com.cactusdigital.cedrus.api.request.UnidadeRequest;
import br.com.cactusdigital.cedrus.domains.repositories.entities.UnidadeEntity;
import br.com.cactusdigital.cedrus.domains.services.mappers.ConverterMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UnidadeSalvarMapper extends ConverterMapper<UnidadeRequest, UnidadeEntity> {

    @Override
    @Mapping(target = "id", ignore = true)
    UnidadeEntity toEntity(UnidadeRequest dto);

    @Override
    @Mapping(target = "id", ignore = true)
    UnidadeEntity updateEntity(@MappingTarget UnidadeEntity entity, UnidadeRequest dto);
}
