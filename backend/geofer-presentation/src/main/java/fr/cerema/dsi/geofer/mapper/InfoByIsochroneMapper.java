package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.InfoByIsochroneDTO;
import org.springframework.stereotype.Component;


@Component
public class InfoByIsochroneMapper {
    public InfoByIsochroneDTO toDTO(Object[] data) {

        return new InfoByIsochroneDTO(
            (Integer) data[0], // gareId
            (Integer) data[1], // nombre
            (Integer) data[2], // annee
            (String) data[3], // typeDonneeCamelcase
            (String) data[4] // nommageCamelcase

        );
    }
}