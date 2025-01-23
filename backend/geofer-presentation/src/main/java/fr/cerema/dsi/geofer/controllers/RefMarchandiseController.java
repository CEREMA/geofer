package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.RefMarchandiseDTO;
import fr.cerema.dsi.geofer.entities.RefMarchandise;
import fr.cerema.dsi.geofer.mapper.RefMarchandiseMapper;
import fr.cerema.dsi.geofer.services.RefMarchandiseService;
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
@Tag(name = "RefMarchandise", description = "Auto generated API")
public class RefMarchandiseController {

  @Autowired
  private RefMarchandiseService refMarchandiseService;

  @Autowired
  private RefMarchandiseMapper refMarchandiseMapper;

  @GetMapping("/ref-marchandise")
  public ResponseEntity<List<RefMarchandiseDTO>> getAllRefMarchandise() {
      List<RefMarchandise> refMarchandises = refMarchandiseService.findAll();
      List<RefMarchandiseDTO> refMarchandiseDTOS = refMarchandises.stream()
              .map(refMarchandiseMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(refMarchandiseDTOS);
  }

  @GetMapping("/ref-marchandise/{id}")
  public ResponseEntity<RefMarchandiseDTO> getRefMarchandiseById(@PathVariable Integer id) {
      RefMarchandise refMarchandise = refMarchandiseService.findById(id);
      if (refMarchandise != null) {
          RefMarchandiseDTO refMarchandiseDTO = refMarchandiseMapper.toDTO(refMarchandise);
          return ResponseEntity.ok(refMarchandiseDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}