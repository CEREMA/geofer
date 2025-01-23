package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.RefClassePassageNiveauDTO;
import fr.cerema.dsi.geofer.entities.RefClassePassageNiveau;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RefClassePassageNiveauMapper {
 
  RefClassePassageNiveauMapper INSTANCE = Mappers.getMapper(RefClassePassageNiveauMapper.class);
 
  RefClassePassageNiveauDTO toDTO(RefClassePassageNiveau refClassePassageNiveau);
 
  RefClassePassageNiveau toEntity(RefClassePassageNiveauDTO RefClassePassageNiveauDTO);
 
}