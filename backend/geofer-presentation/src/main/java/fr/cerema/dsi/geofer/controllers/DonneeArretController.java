package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.DonneeArretDTO;
import fr.cerema.dsi.geofer.entities.DonneeArret;
import fr.cerema.dsi.geofer.mapper.DonneeArretMapper;
import fr.cerema.dsi.geofer.services.DonneeArretService;
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
import fr.cerema.dsi.geofer.dto.DonneesArretDTO;
import fr.cerema.dsi.geofer.mapper.DonneesArretMapper;
import java.lang.Integer;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "DonneeArret", description = "Auto generated API")
public class DonneeArretController {
    @Autowired
    private DonneeArretService donneeArretService;

    @Autowired
    private DonneeArretMapper donneeArretMapper;

    @GetMapping("/donnee-arret")
    public ResponseEntity < List < DonneeArretDTO >> getAllDonneeArret() {
        List < DonneeArret > donneeArrets = donneeArretService.findAll();
        List < DonneeArretDTO > donneeArretDTOS = donneeArrets.stream().map(donneeArretMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(donneeArretDTOS);
    }

    @GetMapping("/donnee-arret/gare/{gareId}")
    public ResponseEntity < List < DonneeArretDTO >> getByGareId(@PathVariable Integer gareId) {
        List < DonneeArret > donneeArrets = donneeArretService.getByGareId(gareId);
        List < DonneeArretDTO > donneeArretDTOs = donneeArrets.stream().map(donneeArretMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(donneeArretDTOs);
    }

    @GetMapping("/donnee-arret/refTypeArret/{refTypeArretId}")
    public ResponseEntity < List < DonneeArretDTO >> getByRefTypeArretId(@PathVariable Integer refTypeArretId) {
        List < DonneeArret > donneeArrets = donneeArretService.getByRefTypeArretId(refTypeArretId);
        List < DonneeArretDTO > donneeArretDTOs = donneeArrets.stream().map(donneeArretMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(donneeArretDTOs);
    }

    @GetMapping("/donnee-arret/{id}")
    public ResponseEntity < DonneeArretDTO > getDonneeArretById(@PathVariable Integer id) {
        DonneeArret donneeArret = donneeArretService.findById(id);
        if (donneeArret != null) {
            DonneeArretDTO donneeArretDTO = donneeArretMapper.toDTO(donneeArret);
            return ResponseEntity.ok(donneeArretDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private DonneesArretMapper donneesArretMapper;

    @GetMapping("/query/donneesArret")
    public ResponseEntity < List < DonneesArretDTO >> getDonneesArret(@RequestParam("gareIds") List < Integer > gareIds) {

        List < Object[] > rawDonneesArret = donneeArretService.getDonneesArret(gareIds);

        List < DonneesArretDTO > dtoList = rawDonneesArret.stream()
            .map(donneesArretMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}