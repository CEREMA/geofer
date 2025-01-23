package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.RefTypeArretDTO;
import fr.cerema.dsi.geofer.entities.RefTypeArret;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RefTypeArretMapper {
 
  RefTypeArretMapper INSTANCE = Mappers.getMapper(RefTypeArretMapper.class);
 
  RefTypeArretDTO toDTO(RefTypeArret refTypeArret);
 
  RefTypeArret toEntity(RefTypeArretDTO RefTypeArretDTO);
 
}