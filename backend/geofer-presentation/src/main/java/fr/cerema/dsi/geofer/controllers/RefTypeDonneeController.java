package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.RefTypeDonneeDTO;
import fr.cerema.dsi.geofer.entities.RefTypeDonnee;
import fr.cerema.dsi.geofer.mapper.RefTypeDonneeMapper;
import fr.cerema.dsi.geofer.services.RefTypeDonneeService;
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
@Tag(name = "RefTypeDonnee", description = "Auto generated API")
public class RefTypeDonneeController {

  @Autowired
  private RefTypeDonneeService refTypeDonneeService;

  @Autowired
  private RefTypeDonneeMapper refTypeDonneeMapper;

  @GetMapping("/ref-type-donnee")
  public ResponseEntity<List<RefTypeDonneeDTO>> getAllRefTypeDonnee() {
      List<RefTypeDonnee> refTypeDonnees = refTypeDonneeService.findAll();
      List<RefTypeDonneeDTO> refTypeDonneeDTOS = refTypeDonnees.stream()
              .map(refTypeDonneeMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(refTypeDonneeDTOS);
  }

  @GetMapping("/ref-type-donnee/{id}")
  public ResponseEntity<RefTypeDonneeDTO> getRefTypeDonneeById(@PathVariable Integer id) {
      RefTypeDonnee refTypeDonnee = refTypeDonneeService.findById(id);
      if (refTypeDonnee != null) {
          RefTypeDonneeDTO refTypeDonneeDTO = refTypeDonneeMapper.toDTO(refTypeDonnee);
          return ResponseEntity.ok(refTypeDonneeDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}