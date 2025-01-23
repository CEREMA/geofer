package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.RefTypeArretDTO;
import fr.cerema.dsi.geofer.entities.RefTypeArret;
import fr.cerema.dsi.geofer.mapper.RefTypeArretMapper;
import fr.cerema.dsi.geofer.services.RefTypeArretService;
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
@Tag(name = "RefTypeArret", description = "Auto generated API")
public class RefTypeArretController {

  @Autowired
  private RefTypeArretService refTypeArretService;

  @Autowired
  private RefTypeArretMapper refTypeArretMapper;

  @GetMapping("/ref-type-arret")
  public ResponseEntity<List<RefTypeArretDTO>> getAllRefTypeArret() {
      List<RefTypeArret> refTypeArrets = refTypeArretService.findAll();
      List<RefTypeArretDTO> refTypeArretDTOS = refTypeArrets.stream()
              .map(refTypeArretMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(refTypeArretDTOS);
  }

  @GetMapping("/ref-type-arret/{id}")
  public ResponseEntity<RefTypeArretDTO> getRefTypeArretById(@PathVariable Integer id) {
      RefTypeArret refTypeArret = refTypeArretService.findById(id);
      if (refTypeArret != null) {
          RefTypeArretDTO refTypeArretDTO = refTypeArretMapper.toDTO(refTypeArret);
          return ResponseEntity.ok(refTypeArretDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}