package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.LigneDTO;
import fr.cerema.dsi.geofer.entities.Ligne;
import fr.cerema.dsi.geofer.mapper.LigneMapper;
import fr.cerema.dsi.geofer.services.LigneService;
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
@Tag(name = "Ligne", description = "Auto generated API")
public class LigneController {

  @Autowired
  private LigneService ligneService;

  @Autowired
  private LigneMapper ligneMapper;

  @GetMapping("/ligne")
  public ResponseEntity<List<LigneDTO>> getAllLigne() {
      List<Ligne> lignes = ligneService.findAll();
      List<LigneDTO> ligneDTOS = lignes.stream()
              .map(ligneMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(ligneDTOS);
  }

  @GetMapping("/ligne/{id}")
  public ResponseEntity<LigneDTO> getLigneById(@PathVariable Integer id) {
      Ligne ligne = ligneService.findById(id);
      if (ligne != null) {
          LigneDTO ligneDTO = ligneMapper.toDTO(ligne);
          return ResponseEntity.ok(ligneDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}