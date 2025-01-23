package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.RefTypeEtablissementDTO;
import fr.cerema.dsi.geofer.entities.RefTypeEtablissement;
import fr.cerema.dsi.geofer.mapper.RefTypeEtablissementMapper;
import fr.cerema.dsi.geofer.services.RefTypeEtablissementService;
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
@Tag(name = "RefTypeEtablissement", description = "Auto generated API")
public class RefTypeEtablissementController {

  @Autowired
  private RefTypeEtablissementService refTypeEtablissementService;

  @Autowired
  private RefTypeEtablissementMapper refTypeEtablissementMapper;

  @GetMapping("/ref-type-etablissement")
  public ResponseEntity<List<RefTypeEtablissementDTO>> getAllRefTypeEtablissement() {
      List<RefTypeEtablissement> refTypeEtablissements = refTypeEtablissementService.findAll();
      List<RefTypeEtablissementDTO> refTypeEtablissementDTOS = refTypeEtablissements.stream()
              .map(refTypeEtablissementMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(refTypeEtablissementDTOS);
  }

  @GetMapping("/ref-type-etablissement/{id}")
  public ResponseEntity<RefTypeEtablissementDTO> getRefTypeEtablissementById(@PathVariable Integer id) {
      RefTypeEtablissement refTypeEtablissement = refTypeEtablissementService.findById(id);
      if (refTypeEtablissement != null) {
          RefTypeEtablissementDTO refTypeEtablissementDTO = refTypeEtablissementMapper.toDTO(refTypeEtablissement);
          return ResponseEntity.ok(refTypeEtablissementDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}