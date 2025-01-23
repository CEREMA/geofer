package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.DonneeIsochroneDTO;
import fr.cerema.dsi.geofer.entities.DonneeIsochrone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import fr.cerema.dsi.geofer.entities.Isochrone;
import fr.cerema.dsi.geofer.entities.RefTypeDonnee;

@Mapper(componentModel = "spring", uses = { IsochroneMapper.class, RefTypeDonneeMapper.class })
public interface DonneeIsochroneMapper {
 
  DonneeIsochroneMapper INSTANCE = Mappers.getMapper(DonneeIsochroneMapper.class);
 
  @Mapping(source = "isochrone", target = "isochrone")
  @Mapping(source = "refTypeDonnee", target = "refTypeDonnee")
  DonneeIsochroneDTO toDTO(DonneeIsochrone donneeIsochrone);
 
  @Mapping(source = "isochrone", target = "isochrone")
  @Mapping(source = "refTypeDonnee", target = "refTypeDonnee")
  DonneeIsochrone toEntity(DonneeIsochroneDTO DonneeIsochroneDTO);
 
}