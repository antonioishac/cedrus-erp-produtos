package br.com.cactusdigital.cedrus.domains.services.mappers.grupo;

import br.com.cactusdigital.cedrus.domains.repositories.entities.GrupoEntity;
import br.com.cactusdigital.cedrus.domains.services.dtos.GrupoDTO;
import br.com.cactusdigital.cedrus.domains.services.mappers.ConverterMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GrupoMapper extends ConverterMapper<GrupoDTO, GrupoEntity> {
}
