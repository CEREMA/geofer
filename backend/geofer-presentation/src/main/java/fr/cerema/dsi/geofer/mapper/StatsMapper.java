package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.StatsDTO;
import org.springframework.stereotype.Component;


@Component
public class StatsMapper {
    public StatsDTO toDTO(Object[] data) {

        return new StatsDTO(
            (String) data[0], // typeDonnee
            (Integer) data[1], // annee
            (Integer) data[2], // nombre
            (Integer) data[3], // gareId
            (String) data[4], // groupeDonnee
            (String) data[5] // libelleTypeIsochrone

        );
    }
}