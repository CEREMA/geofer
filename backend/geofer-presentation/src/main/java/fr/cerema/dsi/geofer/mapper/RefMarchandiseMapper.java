package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.RefMarchandiseDTO;
import fr.cerema.dsi.geofer.entities.RefMarchandise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RefMarchandiseMapper {
 
  RefMarchandiseMapper INSTANCE = Mappers.getMapper(RefMarchandiseMapper.class);
 
  RefMarchandiseDTO toDTO(RefMarchandise refMarchandise);
 
  RefMarchandise toEntity(RefMarchandiseDTO RefMarchandiseDTO);
 
}