package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.LookUpRegionsDTO;
import org.springframework.stereotype.Component;


@Component
public class LookUpRegionsMapper {
    public LookUpRegionsDTO toDTO(Object[] data) {

        return new LookUpRegionsDTO(
            (Integer) data[0], // id
            (String) data[1], // code
            (String) data[2] // nom

        );
    }
}