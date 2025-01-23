package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.RefClassePassageNiveauDTO;
import fr.cerema.dsi.geofer.entities.RefClassePassageNiveau;
import fr.cerema.dsi.geofer.mapper.RefClassePassageNiveauMapper;
import fr.cerema.dsi.geofer.services.RefClassePassageNiveauService;
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
@Tag(name = "RefClassePassageNiveau", description = "Auto generated API")
public class RefClassePassageNiveauController {

  @Autowired
  private RefClassePassageNiveauService refClassePassageNiveauService;

  @Autowired
  private RefClassePassageNiveauMapper refClassePassageNiveauMapper;

  @GetMapping("/ref-classe-passage-niveau")
  public ResponseEntity<List<RefClassePassageNiveauDTO>> getAllRefClassePassageNiveau() {
      List<RefClassePassageNiveau> refClassePassageNiveaus = refClassePassageNiveauService.findAll();
      List<RefClassePassageNiveauDTO> refClassePassageNiveauDTOS = refClassePassageNiveaus.stream()
              .map(refClassePassageNiveauMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(refClassePassageNiveauDTOS);
  }

  @GetMapping("/ref-classe-passage-niveau/{id}")
  public ResponseEntity<RefClassePassageNiveauDTO> getRefClassePassageNiveauById(@PathVariable Integer id) {
      RefClassePassageNiveau refClassePassageNiveau = refClassePassageNiveauService.findById(id);
      if (refClassePassageNiveau != null) {
          RefClassePassageNiveauDTO refClassePassageNiveauDTO = refClassePassageNiveauMapper.toDTO(refClassePassageNiveau);
          return ResponseEntity.ok(refClassePassageNiveauDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}