package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.DptRegionsDTO;
import fr.cerema.dsi.geofer.entities.DptRegions;
import fr.cerema.dsi.geofer.mapper.DptRegionsMapper;
import fr.cerema.dsi.geofer.services.DptRegionsService;
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
import fr.cerema.dsi.geofer.dto.DeptByRegionDTO;
import fr.cerema.dsi.geofer.mapper.DeptByRegionMapper;
import java.lang.String;
import fr.cerema.dsi.geofer.dto.RegionByDeptDTO;
import fr.cerema.dsi.geofer.mapper.RegionByDeptMapper;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "DptRegions", description = "Auto generated API")
public class DptRegionsController {
    @Autowired
    private DptRegionsService dptRegionsService;

    @Autowired
    private DptRegionsMapper dptRegionsMapper;

    @GetMapping("/dpt-regions")
    public ResponseEntity < List < DptRegionsDTO >> getAllDptRegions() {
        List < DptRegions > dptRegionss = dptRegionsService.findAll();
        List < DptRegionsDTO > dptRegionsDTOS = dptRegionss.stream().map(dptRegionsMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dptRegionsDTOS);
    }

    @GetMapping("/dpt-regions/{id}")
    public ResponseEntity < DptRegionsDTO > getDptRegionsById(@PathVariable String id) {
        DptRegions dptRegions = dptRegionsService.findById(id);
        if (dptRegions != null) {
            DptRegionsDTO dptRegionsDTO = dptRegionsMapper.toDTO(dptRegions);
            return ResponseEntity.ok(dptRegionsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private DeptByRegionMapper deptByRegionMapper;

    @GetMapping("/query/deptByRegion")
    public ResponseEntity < List < DeptByRegionDTO >> getDeptByRegion(@RequestParam("code") String code) {
        List < Object[] > rawDeptByRegion = dptRegionsService.getDeptByRegion(code);
        List < DeptByRegionDTO > dtoList = rawDeptByRegion.stream().map(deptByRegionMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @Autowired
    private RegionByDeptMapper regionByDeptMapper;

    @GetMapping("/query/regionByDept")
    public ResponseEntity < List < RegionByDeptDTO >> getRegionByDept(@RequestParam("code") String code) {

        List < Object[] > rawRegionByDept = dptRegionsService.getRegionByDept(code);

        List < RegionByDeptDTO > dtoList = rawRegionByDept.stream()
            .map(regionByDeptMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}