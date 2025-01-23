package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.InstallationTermRefMarchandiseDTO;
import fr.cerema.dsi.geofer.entities.InstallationTermRefMarchandise;
import fr.cerema.dsi.geofer.mapper.InstallationTermRefMarchandiseMapper;
import fr.cerema.dsi.geofer.services.InstallationTermRefMarchandiseService;
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
import org.springframework.web.bind.annotation.RequestParam;
import fr.cerema.dsi.geofer.dto.SearchDTO;
import fr.cerema.dsi.geofer.mapper.SearchMapper;
import java.lang.Integer;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "InstallationTermRefMarchandise", description = "Table de liaison entre installation_terminale_embranchee et ref_marchandise.; En ignorant 'si_par train', on a les marchandises expédiées ou reçues tous modes confondus; En filtrant 'si_par_train', on a les marchandises expédiées ou reçues par train uniquement")
public class InstallationTermRefMarchandiseController {
    @Autowired
    private InstallationTermRefMarchandiseService installationTermRefMarchandiseService;

    @Autowired
    private InstallationTermRefMarchandiseMapper installationTermRefMarchandiseMapper;

    @GetMapping("/installation-term-ref-marchandise")
    public ResponseEntity < List < InstallationTermRefMarchandiseDTO >> getAllInstallationTermRefMarchandise() {
        List < InstallationTermRefMarchandise > installationTermRefMarchandises = installationTermRefMarchandiseService.findAll();
        List < InstallationTermRefMarchandiseDTO > installationTermRefMarchandiseDTOS = installationTermRefMarchandises.stream().map(installationTermRefMarchandiseMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(installationTermRefMarchandiseDTOS);
    }

    @GetMapping("/installation-term-ref-marchandise/installationTerm/{installationTermId}")
    public ResponseEntity < List < InstallationTermRefMarchandiseDTO >> getByInstallationTermId(@PathVariable Integer installationTermId) {
        List < InstallationTermRefMarchandise > installationTermRefMarchandises = installationTermRefMarchandiseService.getByInstallationTermId(installationTermId);
        List < InstallationTermRefMarchandiseDTO > installationTermRefMarchandiseDTOs = installationTermRefMarchandises.stream().map(installationTermRefMarchandiseMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(installationTermRefMarchandiseDTOs);
    }

    @GetMapping("/installation-term-ref-marchandise/refMarchandise/{refMarchandiseId}")
    public ResponseEntity < List < InstallationTermRefMarchandiseDTO >> getByRefMarchandiseId(@PathVariable Integer refMarchandiseId) {
        List < InstallationTermRefMarchandise > installationTermRefMarchandises = installationTermRefMarchandiseService.getByRefMarchandiseId(refMarchandiseId);
        List < InstallationTermRefMarchandiseDTO > installationTermRefMarchandiseDTOs = installationTermRefMarchandises.stream().map(installationTermRefMarchandiseMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(installationTermRefMarchandiseDTOs);
    }

    @GetMapping("/installation-term-ref-marchandise/{id}")
    public ResponseEntity < InstallationTermRefMarchandiseDTO > getInstallationTermRefMarchandiseById(@PathVariable Integer id) {
        InstallationTermRefMarchandise installationTermRefMarchandise = installationTermRefMarchandiseService.findById(id);
        if (installationTermRefMarchandise != null) {
            InstallationTermRefMarchandiseDTO installationTermRefMarchandiseDTO = installationTermRefMarchandiseMapper.toDTO(installationTermRefMarchandise);
            return ResponseEntity.ok(installationTermRefMarchandiseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private SearchMapper searchMapper;

    @GetMapping("/query/search")
    public ResponseEntity < List < SearchDTO >> getSearch(@RequestParam("p_installation_term_id") Integer p_installation_term_id) {

        List < Object[] > rawSearch = installationTermRefMarchandiseService.getSearch(p_installation_term_id);

        List < SearchDTO > dtoList = rawSearch.stream()
            .map(searchMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}