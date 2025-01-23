package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.EtablissementScolaireDTO;
import fr.cerema.dsi.geofer.entities.EtablissementScolaire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import fr.cerema.dsi.geofer.entities.RefTypeEtablissement;

@Mapper(componentModel = "spring", uses = { RefTypeEtablissementMapper.class })
public interface EtablissementScolaireMapper {
 
  EtablissementScolaireMapper INSTANCE = Mappers.getMapper(EtablissementScolaireMapper.class);
 
  @Mapping(source = "refTypeEtablissement", target = "refTypeEtablissement")
  EtablissementScolaireDTO toDTO(EtablissementScolaire etablissementScolaire);
 
  @Mapping(source = "refTypeEtablissement", target = "refTypeEtablissement")
  EtablissementScolaire toEntity(EtablissementScolaireDTO EtablissementScolaireDTO);
 
}