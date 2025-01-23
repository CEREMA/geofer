package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.InstallationTermDTO;
import fr.cerema.dsi.geofer.entities.InstallationTerm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import fr.cerema.dsi.geofer.entities.RefActivitePrincipale;

@Mapper(componentModel = "spring", uses = { RefActivitePrincipaleMapper.class })
public interface InstallationTermMapper {
 
  InstallationTermMapper INSTANCE = Mappers.getMapper(InstallationTermMapper.class);
 
  @Mapping(source = "refActivitePrincipale", target = "refActivitePrincipale")
  InstallationTermDTO toDTO(InstallationTerm installationTerm);
 
  @Mapping(source = "refActivitePrincipale", target = "refActivitePrincipale")
  InstallationTerm toEntity(InstallationTermDTO InstallationTermDTO);
 
}