package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.DonneeIsochroneDTO;
import fr.cerema.dsi.geofer.entities.DonneeIsochrone;
import fr.cerema.dsi.geofer.mapper.DonneeIsochroneMapper;
import fr.cerema.dsi.geofer.services.DonneeIsochroneService;
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
import fr.cerema.dsi.geofer.dto.StatsDTO;
import fr.cerema.dsi.geofer.mapper.StatsMapper;
import java.lang.Integer;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "DonneeIsochrone", description = "Auto generated API")
public class DonneeIsochroneController {
    @Autowired
    private DonneeIsochroneService donneeIsochroneService;

    @Autowired
    private DonneeIsochroneMapper donneeIsochroneMapper;

    @GetMapping("/donnee-isochrone")
    public ResponseEntity < List < DonneeIsochroneDTO >> getAllDonneeIsochrone() {
        List < DonneeIsochrone > donneeIsochrones = donneeIsochroneService.findAll();
        List < DonneeIsochroneDTO > donneeIsochroneDTOS = donneeIsochrones.stream().map(donneeIsochroneMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(donneeIsochroneDTOS);
    }

    @GetMapping("/donnee-isochrone/isochrone/{isochroneId}")
    public ResponseEntity < List < DonneeIsochroneDTO >> getByIsochroneId(@PathVariable Integer isochroneId) {
        List < DonneeIsochrone > donneeIsochrones = donneeIsochroneService.getByIsochroneId(isochroneId);
        List < DonneeIsochroneDTO > donneeIsochroneDTOs = donneeIsochrones.stream().map(donneeIsochroneMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(donneeIsochroneDTOs);
    }

    @GetMapping("/donnee-isochrone/refTypeDonnee/{refTypeDonneeId}")
    public ResponseEntity < List < DonneeIsochroneDTO >> getByRefTypeDonneeId(@PathVariable Integer refTypeDonneeId) {
        List < DonneeIsochrone > donneeIsochrones = donneeIsochroneService.getByRefTypeDonneeId(refTypeDonneeId);
        List < DonneeIsochroneDTO > donneeIsochroneDTOs = donneeIsochrones.stream().map(donneeIsochroneMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(donneeIsochroneDTOs);
    }

    @GetMapping("/donnee-isochrone/{id}")
    public ResponseEntity < DonneeIsochroneDTO > getDonneeIsochroneById(@PathVariable Integer id) {
        DonneeIsochrone donneeIsochrone = donneeIsochroneService.findById(id);
        if (donneeIsochrone != null) {
            DonneeIsochroneDTO donneeIsochroneDTO = donneeIsochroneMapper.toDTO(donneeIsochrone);
            return ResponseEntity.ok(donneeIsochroneDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private StatsMapper statsMapper;

    @GetMapping("/query/stats")
    public ResponseEntity < List < StatsDTO >> getStats(@RequestParam("gareIds") List < Integer > gareIds) {

        List < Object[] > rawStats = donneeIsochroneService.getStats(gareIds);

        List < StatsDTO > dtoList = rawStats.stream()
            .map(statsMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}