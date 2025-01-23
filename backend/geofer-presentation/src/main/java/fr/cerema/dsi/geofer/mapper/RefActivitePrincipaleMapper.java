package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.RefActivitePrincipaleDTO;
import fr.cerema.dsi.geofer.entities.RefActivitePrincipale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RefActivitePrincipaleMapper {
 
  RefActivitePrincipaleMapper INSTANCE = Mappers.getMapper(RefActivitePrincipaleMapper.class);
 
  RefActivitePrincipaleDTO toDTO(RefActivitePrincipale refActivitePrincipale);
 
  RefActivitePrincipale toEntity(RefActivitePrincipaleDTO RefActivitePrincipaleDTO);
 
}