package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.EtablissementScolaireDTO;
import fr.cerema.dsi.geofer.entities.EtablissementScolaire;
import fr.cerema.dsi.geofer.mapper.EtablissementScolaireMapper;
import fr.cerema.dsi.geofer.services.EtablissementScolaireService;
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
@Tag(name = "EtablissementScolaire", description = "Auto generated API")
public class EtablissementScolaireController {

  @Autowired
  private EtablissementScolaireService etablissementScolaireService;

  @Autowired
  private EtablissementScolaireMapper etablissementScolaireMapper;

  @GetMapping("/etablissement-scolaire")
  public ResponseEntity<List<EtablissementScolaireDTO>> getAllEtablissementScolaire() {
      List<EtablissementScolaire> etablissementScolaires = etablissementScolaireService.findAll();
      List<EtablissementScolaireDTO> etablissementScolaireDTOS = etablissementScolaires.stream()
              .map(etablissementScolaireMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(etablissementScolaireDTOS);
  }


  @GetMapping("/etablissement-scolaire/refTypeEtablissement/{refTypeEtablissementId}")
  public ResponseEntity<List<EtablissementScolaireDTO>> getByRefTypeEtablissementId(@PathVariable Integer refTypeEtablissementId) {
    List<EtablissementScolaire> etablissementScolaires = etablissementScolaireService.getByRefTypeEtablissementId(refTypeEtablissementId);
    List<EtablissementScolaireDTO> etablissementScolaireDTOs = etablissementScolaires.stream()
                .map(etablissementScolaireMapper::toDTO)
                .collect(Collectors.toList());
    return ResponseEntity.ok(etablissementScolaireDTOs);
  }

  @GetMapping("/etablissement-scolaire/{id}")
  public ResponseEntity<EtablissementScolaireDTO> getEtablissementScolaireById(@PathVariable Integer id) {
      EtablissementScolaire etablissementScolaire = etablissementScolaireService.findById(id);
      if (etablissementScolaire != null) {
          EtablissementScolaireDTO etablissementScolaireDTO = etablissementScolaireMapper.toDTO(etablissementScolaire);
          return ResponseEntity.ok(etablissementScolaireDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}