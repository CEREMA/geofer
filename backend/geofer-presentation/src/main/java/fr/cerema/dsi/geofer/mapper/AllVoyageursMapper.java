package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.AllVoyageursDTO;
import org.springframework.stereotype.Component;


@Component
public class AllVoyageursMapper {
    public AllVoyageursDTO toDTO(Object[] data) {

        return new AllVoyageursDTO(
            (Integer) data[0], // annee
            (Long) data[1] // totalParAnnee

        );
    }
}