package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.PassageNiveauDTO;
import fr.cerema.dsi.geofer.entities.PassageNiveau;
import fr.cerema.dsi.geofer.mapper.PassageNiveauMapper;
import fr.cerema.dsi.geofer.services.PassageNiveauService;
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
@Tag(name = "PassageNiveau", description = "Auto generated API")
public class PassageNiveauController {

  @Autowired
  private PassageNiveauService passageNiveauService;

  @Autowired
  private PassageNiveauMapper passageNiveauMapper;

  @GetMapping("/passage-niveau")
  public ResponseEntity<List<PassageNiveauDTO>> getAllPassageNiveau() {
      List<PassageNiveau> passageNiveaus = passageNiveauService.findAll();
      List<PassageNiveauDTO> passageNiveauDTOS = passageNiveaus.stream()
              .map(passageNiveauMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(passageNiveauDTOS);
  }


  @GetMapping("/passage-niveau/refClassePassageNiveau/{refClassePassageNiveauId}")
  public ResponseEntity<List<PassageNiveauDTO>> getByRefClassePassageNiveauId(@PathVariable Integer refClassePassageNiveauId) {
    List<PassageNiveau> passageNiveaus = passageNiveauService.getByRefClassePassageNiveauId(refClassePassageNiveauId);
    List<PassageNiveauDTO> passageNiveauDTOs = passageNiveaus.stream()
                .map(passageNiveauMapper::toDTO)
                .collect(Collectors.toList());
    return ResponseEntity.ok(passageNiveauDTOs);
  }

  @GetMapping("/passage-niveau/{id}")
  public ResponseEntity<PassageNiveauDTO> getPassageNiveauById(@PathVariable Integer id) {
      PassageNiveau passageNiveau = passageNiveauService.findById(id);
      if (passageNiveau != null) {
          PassageNiveauDTO passageNiveauDTO = passageNiveauMapper.toDTO(passageNiveau);
          return ResponseEntity.ok(passageNiveauDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}