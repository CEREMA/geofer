package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.DonneeVoyageurDTO;
import fr.cerema.dsi.geofer.entities.DonneeVoyageur;
import fr.cerema.dsi.geofer.mapper.DonneeVoyageurMapper;
import fr.cerema.dsi.geofer.services.DonneeVoyageurService;
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
import fr.cerema.dsi.geofer.dto.AllVoyageursDTO;
import fr.cerema.dsi.geofer.mapper.AllVoyageursMapper;
import java.lang.Integer;
import fr.cerema.dsi.geofer.dto.DonneeVoyageurByGareDTO;
import fr.cerema.dsi.geofer.mapper.DonneeVoyageurByGareMapper;
import fr.cerema.dsi.geofer.dto.FrequentationVoyageurDTO;
import fr.cerema.dsi.geofer.mapper.FrequentationVoyageurMapper;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "DonneeVoyageur", description = "Auto generated API")
public class DonneeVoyageurController {
    @Autowired
    private DonneeVoyageurService donneeVoyageurService;

    @Autowired
    private DonneeVoyageurMapper donneeVoyageurMapper;

    @GetMapping("/donnee-voyageur")
    public ResponseEntity < List < DonneeVoyageurDTO >> getAllDonneeVoyageur() {
        List < DonneeVoyageur > donneeVoyageurs = donneeVoyageurService.findAll();
        List < DonneeVoyageurDTO > donneeVoyageurDTOS = donneeVoyageurs.stream().map(donneeVoyageurMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(donneeVoyageurDTOS);
    }

    @GetMapping("/donnee-voyageur/gare/{gareId}")
    public ResponseEntity < List < DonneeVoyageurDTO >> getByGareId(@PathVariable Integer gareId) {
        List < DonneeVoyageur > donneeVoyageurs = donneeVoyageurService.getByGareId(gareId);
        List < DonneeVoyageurDTO > donneeVoyageurDTOs = donneeVoyageurs.stream().map(donneeVoyageurMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(donneeVoyageurDTOs);
    }

    @GetMapping("/donnee-voyageur/{id}")
    public ResponseEntity < DonneeVoyageurDTO > getDonneeVoyageurById(@PathVariable Integer id) {
        DonneeVoyageur donneeVoyageur = donneeVoyageurService.findById(id);
        if (donneeVoyageur != null) {
            DonneeVoyageurDTO donneeVoyageurDTO = donneeVoyageurMapper.toDTO(donneeVoyageur);
            return ResponseEntity.ok(donneeVoyageurDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private AllVoyageursMapper allVoyageursMapper;

    @GetMapping("/query/allVoyageurs")
    public ResponseEntity < List < AllVoyageursDTO >> getAllVoyageurs(@RequestParam("gareIds") List < Integer > gareIds) {
        List < Object[] > rawAllVoyageurs = donneeVoyageurService.getAllVoyageurs(gareIds);
        List < AllVoyageursDTO > dtoList = rawAllVoyageurs.stream().map(allVoyageursMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @Autowired
    private DonneeVoyageurByGareMapper donneeVoyageurByGareMapper;

    @GetMapping("/query/donneeVoyageurByGare")
    public ResponseEntity < List < DonneeVoyageurByGareDTO >> getDonneeVoyageurByGare(@RequestParam("gareIds") List < Integer > gareIds) {
        List < Object[] > rawDonneeVoyageurByGare = donneeVoyageurService.getDonneeVoyageurByGare(gareIds);
        List < DonneeVoyageurByGareDTO > dtoList = rawDonneeVoyageurByGare.stream().map(donneeVoyageurByGareMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @Autowired
    private FrequentationVoyageurMapper frequentationVoyageurMapper;

    @GetMapping("/query/frequentationVoyageur")
    public ResponseEntity < List < FrequentationVoyageurDTO >> getFrequentationVoyageur(@RequestParam("gareIds") List < Integer > gareIds) {

        List < Object[] > rawFrequentationVoyageur = donneeVoyageurService.getFrequentationVoyageur(gareIds);

        List < FrequentationVoyageurDTO > dtoList = rawFrequentationVoyageur.stream()
            .map(frequentationVoyageurMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}