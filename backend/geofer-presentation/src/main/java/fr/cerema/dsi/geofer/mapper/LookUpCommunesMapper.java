package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.LookUpCommunesDTO;
import org.springframework.stereotype.Component;


@Component
public class LookUpCommunesMapper {
    public LookUpCommunesDTO toDTO(Object[] data) {

        return new LookUpCommunesDTO(
            (Integer) data[0], // id
            (String) data[1], // comNom
            (String) data[2] // comCode

        );
    }
}