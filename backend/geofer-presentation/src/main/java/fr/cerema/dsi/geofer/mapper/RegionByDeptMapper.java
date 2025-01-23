package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.RegionByDeptDTO;
import org.springframework.stereotype.Component;


@Component
public class RegionByDeptMapper {
    public RegionByDeptDTO toDTO(Object[] data) {

        return new RegionByDeptDTO(
            (String) data[0], // departmentCode
            (String) data[1], // departmentName
            (String) data[2], // regionCode
            (String) data[3] // regionName

        );
    }
}