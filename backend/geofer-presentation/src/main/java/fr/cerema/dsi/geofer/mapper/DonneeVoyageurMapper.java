package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.DonneeVoyageurDTO;
import fr.cerema.dsi.geofer.entities.DonneeVoyageur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import fr.cerema.dsi.geofer.entities.Gare;

@Mapper(componentModel = "spring", uses = { GareMapper.class })
public interface DonneeVoyageurMapper {
 
  DonneeVoyageurMapper INSTANCE = Mappers.getMapper(DonneeVoyageurMapper.class);
 
  @Mapping(source = "gare", target = "gare")
  DonneeVoyageurDTO toDTO(DonneeVoyageur donneeVoyageur);
 
  @Mapping(source = "gare", target = "gare")
  DonneeVoyageur toEntity(DonneeVoyageurDTO DonneeVoyageurDTO);
 
}