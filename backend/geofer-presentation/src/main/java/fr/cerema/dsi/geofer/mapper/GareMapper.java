package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.GareDTO;
import fr.cerema.dsi.geofer.entities.Gare;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GareMapper {
 
  GareMapper INSTANCE = Mappers.getMapper(GareMapper.class);
 
  GareDTO toDTO(Gare gare);
 
  Gare toEntity(GareDTO GareDTO);
 
}