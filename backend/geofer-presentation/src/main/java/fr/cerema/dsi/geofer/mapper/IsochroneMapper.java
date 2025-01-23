package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.IsochroneDTO;
import fr.cerema.dsi.geofer.entities.Isochrone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import fr.cerema.dsi.geofer.entities.Gare;
import fr.cerema.dsi.geofer.entities.RefTypeIsochrone;

@Mapper(componentModel = "spring", uses = { GareMapper.class, RefTypeIsochroneMapper.class })
public interface IsochroneMapper {
 
  IsochroneMapper INSTANCE = Mappers.getMapper(IsochroneMapper.class);
 
  @Mapping(source = "gare", target = "gare")
  @Mapping(source = "refTypeIsochrone", target = "refTypeIsochrone")
  IsochroneDTO toDTO(Isochrone isochrone);
 
  @Mapping(source = "gare", target = "gare")
  @Mapping(source = "refTypeIsochrone", target = "refTypeIsochrone")
  Isochrone toEntity(IsochroneDTO IsochroneDTO);
 
}