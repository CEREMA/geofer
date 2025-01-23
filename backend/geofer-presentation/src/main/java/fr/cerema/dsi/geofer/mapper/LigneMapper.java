package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.LigneDTO;
import fr.cerema.dsi.geofer.entities.Ligne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LigneMapper {
 
  LigneMapper INSTANCE = Mappers.getMapper(LigneMapper.class);
 
  LigneDTO toDTO(Ligne ligne);
 
  Ligne toEntity(LigneDTO LigneDTO);
 
}