package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.CommunesDTO;
import fr.cerema.dsi.geofer.entities.Communes;
import fr.cerema.dsi.geofer.mapper.CommunesMapper;
import fr.cerema.dsi.geofer.services.CommunesService;
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
import fr.cerema.dsi.geofer.dto.GaresByCommuneDTO;
import fr.cerema.dsi.geofer.mapper.GaresByCommuneMapper;
import java.lang.String;
import fr.cerema.dsi.geofer.dto.LookUpCommunesDTO;
import fr.cerema.dsi.geofer.mapper.LookUpCommunesMapper;
import fr.cerema.dsi.geofer.dto.LookUpCommunesDetailsDTO;
import fr.cerema.dsi.geofer.mapper.LookUpCommunesDetailsMapper;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Communes", description = "Auto generated API")
public class CommunesController {
    @Autowired
    private CommunesService communesService;

    @Autowired
    private CommunesMapper communesMapper;

    @GetMapping("/communes")
    public ResponseEntity < List < CommunesDTO >> getAllCommunes() {
        List < Communes > communess = communesService.findAll();
        List < CommunesDTO > communesDTOS = communess.stream().map(communesMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(communesDTOS);
    }

    @GetMapping("/communes/{id}")
    public ResponseEntity < CommunesDTO > getCommunesById(@PathVariable Integer id) {
        Communes communes = communesService.findById(id);
        if (communes != null) {
            CommunesDTO communesDTO = communesMapper.toDTO(communes);
            return ResponseEntity.ok(communesDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private GaresByCommuneMapper garesByCommuneMapper;

    @GetMapping("/query/garesByCommune")
    public ResponseEntity < List < GaresByCommuneDTO >> getGaresByCommune(@RequestParam("insee_commune") String insee_commune) {
        List < Object[] > rawGaresByCommune = communesService.getGaresByCommune(insee_commune);
        List < GaresByCommuneDTO > dtoList = rawGaresByCommune.stream().map(garesByCommuneMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @Autowired
    private LookUpCommunesMapper lookUpCommunesMapper;

    @GetMapping("/query/lookUpCommunes")
    public ResponseEntity < List < LookUpCommunesDTO >> getLookUpCommunes() {
        List < Object[] > rawLookUpCommunes = communesService.getLookUpCommunes();
        List < LookUpCommunesDTO > dtoList = rawLookUpCommunes.stream().map(lookUpCommunesMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @Autowired
    private LookUpCommunesDetailsMapper lookUpCommunesDetailsMapper;

    @GetMapping("/query/lookUpCommunesDetails")
    public ResponseEntity < List < LookUpCommunesDetailsDTO >> getLookUpCommunesDetails(@RequestParam("insee_code") String insee_code) {

        List < Object[] > rawLookUpCommunesDetails = communesService.getLookUpCommunesDetails(insee_code);

        List < LookUpCommunesDetailsDTO > dtoList = rawLookUpCommunesDetails.stream()
            .map(lookUpCommunesDetailsMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}