package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.IsochroneDTO;
import fr.cerema.dsi.geofer.entities.Isochrone;
import fr.cerema.dsi.geofer.mapper.IsochroneMapper;
import fr.cerema.dsi.geofer.services.IsochroneService;
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
import fr.cerema.dsi.geofer.dto.InfoByIsochroneDTO;
import fr.cerema.dsi.geofer.mapper.InfoByIsochroneMapper;
import java.lang.Integer;
import fr.cerema.dsi.geofer.dto.ZoomIsochroneDTO;
import fr.cerema.dsi.geofer.mapper.ZoomIsochroneMapper;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Isochrone", description = "Auto generated API")
public class IsochroneController {
    @Autowired
    private IsochroneService isochroneService;

    @Autowired
    private IsochroneMapper isochroneMapper;

    @GetMapping("/isochrone")
    public ResponseEntity < List < IsochroneDTO >> getAllIsochrone() {
        List < Isochrone > isochrones = isochroneService.findAll();
        List < IsochroneDTO > isochroneDTOS = isochrones.stream().map(isochroneMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(isochroneDTOS);
    }

    @GetMapping("/isochrone/gare/{gareId}")
    public ResponseEntity < List < IsochroneDTO >> getByGareId(@PathVariable Integer gareId) {
        List < Isochrone > isochrones = isochroneService.getByGareId(gareId);
        List < IsochroneDTO > isochroneDTOs = isochrones.stream().map(isochroneMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(isochroneDTOs);
    }

    @GetMapping("/isochrone/refTypeIsochrone/{refTypeIsochroneId}")
    public ResponseEntity < List < IsochroneDTO >> getByRefTypeIsochroneId(@PathVariable Integer refTypeIsochroneId) {
        List < Isochrone > isochrones = isochroneService.getByRefTypeIsochroneId(refTypeIsochroneId);
        List < IsochroneDTO > isochroneDTOs = isochrones.stream().map(isochroneMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(isochroneDTOs);
    }

    @GetMapping("/isochrone/{id}")
    public ResponseEntity < IsochroneDTO > getIsochroneById(@PathVariable Integer id) {
        Isochrone isochrone = isochroneService.findById(id);
        if (isochrone != null) {
            IsochroneDTO isochroneDTO = isochroneMapper.toDTO(isochrone);
            return ResponseEntity.ok(isochroneDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private InfoByIsochroneMapper infoByIsochroneMapper;

    @GetMapping("/query/infoByIsochrone")
    public ResponseEntity < List < InfoByIsochroneDTO >> getInfoByIsochrone(@RequestParam("gareIds") List < Integer > gareIds) {
        List < Object[] > rawInfoByIsochrone = isochroneService.getInfoByIsochrone(gareIds);
        List < InfoByIsochroneDTO > dtoList = rawInfoByIsochrone.stream().map(infoByIsochroneMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @Autowired
    private ZoomIsochroneMapper zoomIsochroneMapper;

    @GetMapping("/query/zoomIsochrone")
    public ResponseEntity < List < ZoomIsochroneDTO >> getZoomIsochrone(@RequestParam("gareIds") List < Integer > gareIds) {

        List < Object[] > rawZoomIsochrone = isochroneService.getZoomIsochrone(gareIds);

        List < ZoomIsochroneDTO > dtoList = rawZoomIsochrone.stream()
            .map(zoomIsochroneMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}