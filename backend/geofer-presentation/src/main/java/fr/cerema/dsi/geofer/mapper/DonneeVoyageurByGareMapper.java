package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.DonneeVoyageurByGareDTO;
import org.springframework.stereotype.Component;


@Component
public class DonneeVoyageurByGareMapper {
    public DonneeVoyageurByGareDTO toDTO(Object[] data) {

        return new DonneeVoyageurByGareDTO(
            (Integer) data[0], // id
            (Integer) data[1], // annee
            (Integer) data[2], // nombreVoyageur
            (Integer) data[3] // gareId

        );
    }
}