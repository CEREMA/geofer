package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.DepartementsDTO;
import fr.cerema.dsi.geofer.entities.Departements;
import fr.cerema.dsi.geofer.mapper.DepartementsMapper;
import fr.cerema.dsi.geofer.services.DepartementsService;
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
import fr.cerema.dsi.geofer.dto.LookUpDepartementsDTO;
import fr.cerema.dsi.geofer.mapper.LookUpDepartementsMapper;
import fr.cerema.dsi.geofer.dto.SearchDepartementsDTO;
import fr.cerema.dsi.geofer.mapper.SearchDepartementsMapper;
import java.lang.String;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Departements", description = "Auto generated API")
public class DepartementsController {
    @Autowired
    private DepartementsService departementsService;

    @Autowired
    private DepartementsMapper departementsMapper;

    @GetMapping("/departements")
    public ResponseEntity < List < DepartementsDTO >> getAllDepartements() {
        List < Departements > departementss = departementsService.findAll();
        List < DepartementsDTO > departementsDTOS = departementss.stream().map(departementsMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(departementsDTOS);
    }

    @GetMapping("/departements/{id}")
    public ResponseEntity < DepartementsDTO > getDepartementsById(@PathVariable Integer id) {
        Departements departements = departementsService.findById(id);
        if (departements != null) {
            DepartementsDTO departementsDTO = departementsMapper.toDTO(departements);
            return ResponseEntity.ok(departementsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private LookUpDepartementsMapper lookUpDepartementsMapper;

    @GetMapping("/query/lookUpDepartements")
    public ResponseEntity < List < LookUpDepartementsDTO >> getLookUpDepartements() {
        List < Object[] > rawLookUpDepartements = departementsService.getLookUpDepartements();
        List < LookUpDepartementsDTO > dtoList = rawLookUpDepartements.stream().map(lookUpDepartementsMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @Autowired
    private SearchDepartementsMapper searchDepartementsMapper;

    @GetMapping("/query/searchDepartements")
    public ResponseEntity < List < SearchDepartementsDTO >> getSearchDepartements(@RequestParam("code") String code) {

        List < Object[] > rawSearchDepartements = departementsService.getSearchDepartements(code);

        List < SearchDepartementsDTO > dtoList = rawSearchDepartements.stream()
            .map(searchDepartementsMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}