package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.PassageNiveauDTO;
import fr.cerema.dsi.geofer.entities.PassageNiveau;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import fr.cerema.dsi.geofer.entities.RefClassePassageNiveau;

@Mapper(componentModel = "spring", uses = { RefClassePassageNiveauMapper.class })
public interface PassageNiveauMapper {
 
  PassageNiveauMapper INSTANCE = Mappers.getMapper(PassageNiveauMapper.class);
 
  @Mapping(source = "refClassePassageNiveau", target = "refClassePassageNiveau")
  PassageNiveauDTO toDTO(PassageNiveau passageNiveau);
 
  @Mapping(source = "refClassePassageNiveau", target = "refClassePassageNiveau")
  PassageNiveau toEntity(PassageNiveauDTO PassageNiveauDTO);
 
}