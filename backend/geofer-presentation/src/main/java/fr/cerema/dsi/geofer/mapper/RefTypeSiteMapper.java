package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.RefTypeSiteDTO;
import fr.cerema.dsi.geofer.entities.RefTypeSite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RefTypeSiteMapper {
 
  RefTypeSiteMapper INSTANCE = Mappers.getMapper(RefTypeSiteMapper.class);
 
  RefTypeSiteDTO toDTO(RefTypeSite refTypeSite);
 
  RefTypeSite toEntity(RefTypeSiteDTO RefTypeSiteDTO);
 
}