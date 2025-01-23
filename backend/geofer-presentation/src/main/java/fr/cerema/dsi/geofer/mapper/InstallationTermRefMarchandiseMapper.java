package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.InstallationTermRefMarchandiseDTO;
import fr.cerema.dsi.geofer.entities.InstallationTermRefMarchandise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import fr.cerema.dsi.geofer.entities.InstallationTerm;
import fr.cerema.dsi.geofer.entities.RefMarchandise;

@Mapper(componentModel = "spring", uses = { InstallationTermMapper.class, RefMarchandiseMapper.class })
public interface InstallationTermRefMarchandiseMapper {
 
  InstallationTermRefMarchandiseMapper INSTANCE = Mappers.getMapper(InstallationTermRefMarchandiseMapper.class);
 
  @Mapping(source = "installationTerm", target = "installationTerm")
  @Mapping(source = "refMarchandise", target = "refMarchandise")
  InstallationTermRefMarchandiseDTO toDTO(InstallationTermRefMarchandise installationTermRefMarchandise);
 
  @Mapping(source = "installationTerm", target = "installationTerm")
  @Mapping(source = "refMarchandise", target = "refMarchandise")
  InstallationTermRefMarchandise toEntity(InstallationTermRefMarchandiseDTO InstallationTermRefMarchandiseDTO);
 
}