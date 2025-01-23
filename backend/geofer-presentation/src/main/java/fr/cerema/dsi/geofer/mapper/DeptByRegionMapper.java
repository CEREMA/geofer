package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.DeptByRegionDTO;
import org.springframework.stereotype.Component;


@Component
public class DeptByRegionMapper {
    public DeptByRegionDTO toDTO(Object[] data) {

        return new DeptByRegionDTO(
            (String) data[0], // departmentCode
            (String) data[1], // departmentName
            (String) data[2], // regionCode
            (String) data[3] // regionName

        );
    }
}