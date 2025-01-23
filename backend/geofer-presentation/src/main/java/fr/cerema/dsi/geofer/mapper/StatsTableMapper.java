package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.StatsTableDTO;
import org.springframework.stereotype.Component;


@Component
public class StatsTableMapper {
    public StatsTableDTO toDTO(Object[] data) {

        return new StatsTableDTO(
            (String) data[0], // nomGare
            (String) data[1], // statutGare
            (Integer) data[2], // nombreArretsTotal
            (String) data[3], // nomAom
            (Integer) data[4], // anneevoyageurs
            (Integer) data[5], // anneearrets
            (Integer) data[6], // voyageurs
            (Integer) data[7] // gareId

        );
    }
}