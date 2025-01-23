package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.CommunesDTO;
import fr.cerema.dsi.geofer.entities.Communes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommunesMapper {
 
  CommunesMapper INSTANCE = Mappers.getMapper(CommunesMapper.class);
 
  CommunesDTO toDTO(Communes communes);
 
  Communes toEntity(CommunesDTO CommunesDTO);
 
}