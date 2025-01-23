package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.DonneeArretDTO;
import fr.cerema.dsi.geofer.entities.DonneeArret;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import fr.cerema.dsi.geofer.entities.Gare;
import fr.cerema.dsi.geofer.entities.RefTypeArret;

@Mapper(componentModel = "spring", uses = { GareMapper.class, RefTypeArretMapper.class })
public interface DonneeArretMapper {
 
  DonneeArretMapper INSTANCE = Mappers.getMapper(DonneeArretMapper.class);
 
  @Mapping(source = "gare", target = "gare")
  @Mapping(source = "refTypeArret", target = "refTypeArret")
  DonneeArretDTO toDTO(DonneeArret donneeArret);
 
  @Mapping(source = "gare", target = "gare")
  @Mapping(source = "refTypeArret", target = "refTypeArret")
  DonneeArret toEntity(DonneeArretDTO DonneeArretDTO);
 
}