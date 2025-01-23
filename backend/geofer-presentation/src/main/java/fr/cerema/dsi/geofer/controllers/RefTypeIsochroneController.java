package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.RefTypeIsochroneDTO;
import fr.cerema.dsi.geofer.entities.RefTypeIsochrone;
import fr.cerema.dsi.geofer.mapper.RefTypeIsochroneMapper;
import fr.cerema.dsi.geofer.services.RefTypeIsochroneService;
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
@Tag(name = "RefTypeIsochrone", description = "Auto generated API")
public class RefTypeIsochroneController {

  @Autowired
  private RefTypeIsochroneService refTypeIsochroneService;

  @Autowired
  private RefTypeIsochroneMapper refTypeIsochroneMapper;

  @GetMapping("/ref-type-isochrone")
  public ResponseEntity<List<RefTypeIsochroneDTO>> getAllRefTypeIsochrone() {
      List<RefTypeIsochrone> refTypeIsochrones = refTypeIsochroneService.findAll();
      List<RefTypeIsochroneDTO> refTypeIsochroneDTOS = refTypeIsochrones.stream()
              .map(refTypeIsochroneMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(refTypeIsochroneDTOS);
  }

  @GetMapping("/ref-type-isochrone/{id}")
  public ResponseEntity<RefTypeIsochroneDTO> getRefTypeIsochroneById(@PathVariable Integer id) {
      RefTypeIsochrone refTypeIsochrone = refTypeIsochroneService.findById(id);
      if (refTypeIsochrone != null) {
          RefTypeIsochroneDTO refTypeIsochroneDTO = refTypeIsochroneMapper.toDTO(refTypeIsochrone);
          return ResponseEntity.ok(refTypeIsochroneDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}