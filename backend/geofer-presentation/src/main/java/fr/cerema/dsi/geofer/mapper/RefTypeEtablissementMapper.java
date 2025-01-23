package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.RefTypeEtablissementDTO;
import fr.cerema.dsi.geofer.entities.RefTypeEtablissement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RefTypeEtablissementMapper {
 
  RefTypeEtablissementMapper INSTANCE = Mappers.getMapper(RefTypeEtablissementMapper.class);
 
  RefTypeEtablissementDTO toDTO(RefTypeEtablissement refTypeEtablissement);
 
  RefTypeEtablissement toEntity(RefTypeEtablissementDTO RefTypeEtablissementDTO);
 
}