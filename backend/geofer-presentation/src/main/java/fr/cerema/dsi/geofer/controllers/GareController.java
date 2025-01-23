package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.GareDTO;
import fr.cerema.dsi.geofer.entities.Gare;
import fr.cerema.dsi.geofer.mapper.GareMapper;
import fr.cerema.dsi.geofer.services.GareService;
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
import fr.cerema.dsi.geofer.dto.ListGareDTO;
import fr.cerema.dsi.geofer.mapper.ListGareMapper;
import java.lang.Integer;
import fr.cerema.dsi.geofer.dto.StatsTableDTO;
import fr.cerema.dsi.geofer.mapper.StatsTableMapper;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Gare", description = "Auto generated API")
public class GareController {
    @Autowired
    private GareService gareService;

    @Autowired
    private GareMapper gareMapper;

    @GetMapping("/gare")
    public ResponseEntity < List < GareDTO >> getAllGare() {
        List < Gare > gares = gareService.findAll();
        List < GareDTO > gareDTOS = gares.stream().map(gareMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(gareDTOS);
    }

    @GetMapping("/gare/{id}")
    public ResponseEntity < GareDTO > getGareById(@PathVariable Integer id) {
        Gare gare = gareService.findById(id);
        if (gare != null) {
            GareDTO gareDTO = gareMapper.toDTO(gare);
            return ResponseEntity.ok(gareDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private ListGareMapper listGareMapper;

    @GetMapping("/query/listGare")
    public ResponseEntity < List < ListGareDTO >> getListGare(@RequestParam("gareIds") List < Integer > gareIds) {
        List < Object[] > rawListGare = gareService.getListGare(gareIds);
        List < ListGareDTO > dtoList = rawListGare.stream().map(listGareMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @Autowired
    private StatsTableMapper statsTableMapper;

    @GetMapping("/query/statsTable")
    public ResponseEntity < List < StatsTableDTO >> getStatsTable(@RequestParam("gareIds") List < Integer > gareIds) {

        List < Object[] > rawStatsTable = gareService.getStatsTable(gareIds);

        List < StatsTableDTO > dtoList = rawStatsTable.stream()
            .map(statsTableMapper::toDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}