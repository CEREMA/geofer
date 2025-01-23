package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.RefActivitePrincipaleDTO;
import fr.cerema.dsi.geofer.entities.RefActivitePrincipale;
import fr.cerema.dsi.geofer.mapper.RefActivitePrincipaleMapper;
import fr.cerema.dsi.geofer.services.RefActivitePrincipaleService;
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
@Tag(name = "RefActivitePrincipale", description = "Auto generated API")
public class RefActivitePrincipaleController {

  @Autowired
  private RefActivitePrincipaleService refActivitePrincipaleService;

  @Autowired
  private RefActivitePrincipaleMapper refActivitePrincipaleMapper;

  @GetMapping("/ref-activite-principale")
  public ResponseEntity<List<RefActivitePrincipaleDTO>> getAllRefActivitePrincipale() {
      List<RefActivitePrincipale> refActivitePrincipales = refActivitePrincipaleService.findAll();
      List<RefActivitePrincipaleDTO> refActivitePrincipaleDTOS = refActivitePrincipales.stream()
              .map(refActivitePrincipaleMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(refActivitePrincipaleDTOS);
  }

  @GetMapping("/ref-activite-principale/{id}")
  public ResponseEntity<RefActivitePrincipaleDTO> getRefActivitePrincipaleById(@PathVariable Integer id) {
      RefActivitePrincipale refActivitePrincipale = refActivitePrincipaleService.findById(id);
      if (refActivitePrincipale != null) {
          RefActivitePrincipaleDTO refActivitePrincipaleDTO = refActivitePrincipaleMapper.toDTO(refActivitePrincipale);
          return ResponseEntity.ok(refActivitePrincipaleDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}