package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.DptRegionsDTO;
import fr.cerema.dsi.geofer.entities.DptRegions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DptRegionsMapper {
 
  DptRegionsMapper INSTANCE = Mappers.getMapper(DptRegionsMapper.class);
 
  DptRegionsDTO toDTO(DptRegions dptRegions);
 
  DptRegions toEntity(DptRegionsDTO DptRegionsDTO);
 
}