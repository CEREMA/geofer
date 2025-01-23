package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.ReseauFerroviaireDTO;
import fr.cerema.dsi.geofer.entities.ReseauFerroviaire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReseauFerroviaireMapper {
 
  ReseauFerroviaireMapper INSTANCE = Mappers.getMapper(ReseauFerroviaireMapper.class);
 
  ReseauFerroviaireDTO toDTO(ReseauFerroviaire reseauFerroviaire);
 
  ReseauFerroviaire toEntity(ReseauFerroviaireDTO ReseauFerroviaireDTO);
 
}