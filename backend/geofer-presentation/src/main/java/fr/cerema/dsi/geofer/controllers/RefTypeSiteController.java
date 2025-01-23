package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.RefTypeSiteDTO;
import fr.cerema.dsi.geofer.entities.RefTypeSite;
import fr.cerema.dsi.geofer.mapper.RefTypeSiteMapper;
import fr.cerema.dsi.geofer.services.RefTypeSiteService;
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
@Tag(name = "RefTypeSite", description = "Auto generated API")
public class RefTypeSiteController {

  @Autowired
  private RefTypeSiteService refTypeSiteService;

  @Autowired
  private RefTypeSiteMapper refTypeSiteMapper;

  @GetMapping("/ref-type-site")
  public ResponseEntity<List<RefTypeSiteDTO>> getAllRefTypeSite() {
      List<RefTypeSite> refTypeSites = refTypeSiteService.findAll();
      List<RefTypeSiteDTO> refTypeSiteDTOS = refTypeSites.stream()
              .map(refTypeSiteMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(refTypeSiteDTOS);
  }

  @GetMapping("/ref-type-site/{id}")
  public ResponseEntity<RefTypeSiteDTO> getRefTypeSiteById(@PathVariable Integer id) {
      RefTypeSite refTypeSite = refTypeSiteService.findById(id);
      if (refTypeSite != null) {
          RefTypeSiteDTO refTypeSiteDTO = refTypeSiteMapper.toDTO(refTypeSite);
          return ResponseEntity.ok(refTypeSiteDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}