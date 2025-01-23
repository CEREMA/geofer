package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.RefTypeIsochroneDTO;
import fr.cerema.dsi.geofer.entities.RefTypeIsochrone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RefTypeIsochroneMapper {
 
  RefTypeIsochroneMapper INSTANCE = Mappers.getMapper(RefTypeIsochroneMapper.class);
 
  RefTypeIsochroneDTO toDTO(RefTypeIsochrone refTypeIsochrone);
 
  RefTypeIsochrone toEntity(RefTypeIsochroneDTO RefTypeIsochroneDTO);
 
}