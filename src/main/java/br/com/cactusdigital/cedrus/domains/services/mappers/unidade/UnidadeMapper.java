package br.com.cactusdigital.cedrus.domains.services.mappers.unidade;

import br.com.cactusdigital.cedrus.domains.repositories.entities.UnidadeEntity;
import br.com.cactusdigital.cedrus.domains.services.dtos.UnidadeDTO;
import br.com.cactusdigital.cedrus.domains.services.mappers.ConverterMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnidadeMapper extends ConverterMapper<UnidadeDTO, UnidadeEntity> {
}
