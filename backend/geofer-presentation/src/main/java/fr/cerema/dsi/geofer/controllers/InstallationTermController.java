package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.InstallationTermDTO;
import fr.cerema.dsi.geofer.entities.InstallationTerm;
import fr.cerema.dsi.geofer.mapper.InstallationTermMapper;
import fr.cerema.dsi.geofer.services.InstallationTermService;
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
@Tag(name = "InstallationTerm", description = "Auto generated API")
public class InstallationTermController {

  @Autowired
  private InstallationTermService installationTermService;

  @Autowired
  private InstallationTermMapper installationTermMapper;

  @GetMapping("/installation-term")
  public ResponseEntity<List<InstallationTermDTO>> getAllInstallationTerm() {
      List<InstallationTerm> installationTerms = installationTermService.findAll();
      List<InstallationTermDTO> installationTermDTOS = installationTerms.stream()
              .map(installationTermMapper::toDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(installationTermDTOS);
  }


  @GetMapping("/installation-term/refActivitePrincipale/{refActivitePrincipaleId}")
  public ResponseEntity<List<InstallationTermDTO>> getByRefActivitePrincipaleId(@PathVariable Integer refActivitePrincipaleId) {
    List<InstallationTerm> installationTerms = installationTermService.getByRefActivitePrincipaleId(refActivitePrincipaleId);
    List<InstallationTermDTO> installationTermDTOs = installationTerms.stream()
                .map(installationTermMapper::toDTO)
                .collect(Collectors.toList());
    return ResponseEntity.ok(installationTermDTOs);
  }

  @GetMapping("/installation-term/{id}")
  public ResponseEntity<InstallationTermDTO> getInstallationTermById(@PathVariable Integer id) {
      InstallationTerm installationTerm = installationTermService.findById(id);
      if (installationTerm != null) {
          InstallationTermDTO installationTermDTO = installationTermMapper.toDTO(installationTerm);
          return ResponseEntity.ok(installationTermDTO);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

}