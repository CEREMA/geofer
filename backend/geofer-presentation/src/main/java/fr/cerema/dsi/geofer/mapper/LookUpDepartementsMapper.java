package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.LookUpDepartementsDTO;
import org.springframework.stereotype.Component;


@Component
public class LookUpDepartementsMapper {
    public LookUpDepartementsDTO toDTO(Object[] data) {

        return new LookUpDepartementsDTO(
            (Integer) data[0], // id
            (String) data[1], // code
            (String) data[2] // nom

        );
    }
}