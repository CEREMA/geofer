package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.RegionsDTO;
import fr.cerema.dsi.geofer.entities.Regions;
import fr.cerema.dsi.geofer.mapper.RegionsMapper;
import fr.cerema.dsi.geofer.services.RegionsService;
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
import fr.cerema.dsi.geofer.dto.LookUpRegionsDTO;
import fr.cerema.dsi.geofer.mapper.LookUpRegionsMapper;
import fr.cerema.dsi.geofer.dto.RegionsByUIDDTO;
import fr.cerema.dsi.geofer.mapper.RegionsByUIDMapper;
import java.lang.Integer;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Regions", description = "Auto generated API")
public class RegionsController {
    @Autowired
    private RegionsService regionsService;

    @Autowired
    private RegionsMapper regionsMapper;

    @GetMapping("/regions")
    public ResponseEntity < List < RegionsDTO >> getAllRegions() {
        List < Regions > regionss = regionsService.findAll();
        List < RegionsDTO > regionsDTOS = regionss.stream().map(regionsMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(regionsDTOS);
    }

    @GetMapping("/regions/{id}")
    public ResponseEntity < RegionsDTO > getRegionsById(@PathVariable Integer id) {
        Regions regions = regionsService.findById(id);
        if (regions != null) {
            RegionsDTO regionsDTO = regionsMapper.toDTO(regions);
            return ResponseEntity.ok(regionsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private LookUpRegionsMapper lookUpRegionsMapper;

    @GetMapping("/query/lookUpRegions")
    public ResponseEntity < List < LookUpRegionsDTO >> getLookUpRegions() {
        List < Object[] > rawLookUpRegions = regionsService.getLookUpRegions();
        List < LookUpRegionsDTO > dtoList = rawLookUpRegions.stream().map(lookUpRegionsMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @Autowired
    private RegionsByUIDMapper regionsByUIDMapper;

    @GetMapping("/query/regionsByUID")
    public ResponseEntity < List < RegionsByUIDDTO >> getRegionsByUID(@RequestParam("id") Integer id) {

        List < Object[] > rawRegionsByUID = regionsService.getRegionsByUID(id);

        List < RegionsByUIDDTO > dtoList = rawRegionsByUID.stream()
            .map(regionsByUIDMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}