package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.DonneesArretDTO;
import org.springframework.stereotype.Component;


@Component
public class DonneesArretMapper {
    public DonneesArretDTO toDTO(Object[] data) {

        return new DonneesArretDTO(
            (Integer) data[0], // annee
            (Integer) data[1], // refTypeArret
            (String) data[2], // libelle
            (Integer) data[3], // nombreArret
            (Integer) data[4], // gareId
            (Integer) data[5], // id
            (String) data[6] // nomGare

        );
    }
}