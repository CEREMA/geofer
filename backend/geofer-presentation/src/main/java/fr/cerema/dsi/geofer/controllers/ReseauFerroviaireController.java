package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.ReseauFerroviaireDTO;
import fr.cerema.dsi.geofer.entities.ReseauFerroviaire;
import fr.cerema.dsi.geofer.mapper.ReseauFerroviaireMapper;
import fr.cerema.dsi.geofer.services.ReseauFerroviaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "ReseauFerroviaire", description = "Auto generated API")
public class ReseauFerroviaireController {

  @Autowired
  private ReseauFerroviaireService reseauFerroviaireService;

  @Autowired
  private ReseauFerroviaireMapper reseauFerroviaireMapper;

  @GetMapping("/reseau-ferroviaire")
  public ResponseEntity<List<ReseauFerroviaireDTO>> getAllReseauFerroviaire() {
      List<ReseauFerroviaire> reseauFerroviaires = reseauFerroviaireService.findAll();
      List<ReseauFerroviaireDTO> reseauFerroviaireDTOS = reseauFerroviaires.stream()
              .map(reseauFerroviaireMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(reseauFerroviaireDTOS);
  }

  @GetMapping("/reseau-ferroviaire/{id}")
  public ResponseEntity<ReseauFerroviaireDTO> getReseauFerroviaireById(@PathVariable Integer id) {
      ReseauFerroviaire reseauFerroviaire = reseauFerroviaireService.findById(id);
      if (reseauFerroviaire != null) {
          ReseauFerroviaireDTO reseauFerroviaireDTO = reseauFerroviaireMapper.toDTO(reseauFerroviaire);
          return ResponseEntity.ok(reseauFerroviaireDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}