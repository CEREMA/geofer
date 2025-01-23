package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.RegionsDTO;
import fr.cerema.dsi.geofer.entities.Regions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegionsMapper {
 
  RegionsMapper INSTANCE = Mappers.getMapper(RegionsMapper.class);
 
  RegionsDTO toDTO(Regions regions);
 
  Regions toEntity(RegionsDTO RegionsDTO);
 
}