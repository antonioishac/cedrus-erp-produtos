package br.com.cactusdigital.cedrus.domains.services.mappers.grupo;

import br.com.cactusdigital.cedrus.api.request.GrupoRequest;
import br.com.cactusdigital.cedrus.domains.repositories.entities.GrupoEntity;
import br.com.cactusdigital.cedrus.domains.services.mappers.ConverterMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GrupoSalvarMapper extends ConverterMapper<GrupoRequest, GrupoEntity> {

    @Override
    @Mapping(target = "id", ignore = true)
    GrupoEntity toEntity(GrupoRequest dto);

    @Override
    @Mapping(target = "id", ignore = true)
    GrupoEntity updateEntity(@MappingTarget GrupoEntity entity, GrupoRequest dto);
}
