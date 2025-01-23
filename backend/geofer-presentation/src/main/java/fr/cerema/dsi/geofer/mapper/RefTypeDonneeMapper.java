package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.RefTypeDonneeDTO;
import fr.cerema.dsi.geofer.entities.RefTypeDonnee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RefTypeDonneeMapper {
 
  RefTypeDonneeMapper INSTANCE = Mappers.getMapper(RefTypeDonneeMapper.class);
 
  RefTypeDonneeDTO toDTO(RefTypeDonnee refTypeDonnee);
 
  RefTypeDonnee toEntity(RefTypeDonneeDTO RefTypeDonneeDTO);
 
}