package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.FrequentationVoyageurDTO;
import org.springframework.stereotype.Component;


@Component
public class FrequentationVoyageurMapper {
    public FrequentationVoyageurDTO toDTO(Object[] data) {

        return new FrequentationVoyageurDTO(
            (Integer) data[0], // gareId
            (String) data[1], // nomGare
            (Integer) data[2], // annee
            (Integer) data[3], // nombreVoyageur
            (Boolean) data[4] // siOuverte

        );
    }
}