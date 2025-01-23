package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.DepartementsDTO;
import fr.cerema.dsi.geofer.entities.Departements;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartementsMapper {
 
  DepartementsMapper INSTANCE = Mappers.getMapper(DepartementsMapper.class);
 
  DepartementsDTO toDTO(Departements departements);
 
  Departements toEntity(DepartementsDTO DepartementsDTO);
 
}