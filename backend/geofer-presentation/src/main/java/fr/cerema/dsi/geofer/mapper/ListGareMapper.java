package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.ListGareDTO;
import org.springframework.stereotype.Component;


@Component
public class ListGareMapper {
    public ListGareDTO toDTO(Object[] data) {

        return new ListGareDTO(
            (Integer) data[0], // codeUic
            (Integer) data[1], // gareId
            (String) data[2], // inseeCommune
            (String) data[3], // inseeDepartement
            (String) data[4], // nomAom
            (String) data[5], // nomCommune
            (String) data[6], // nomGare
            (Boolean) data[7], // siAutomatesTer
            (Boolean) data[8], // siAutomatesTgvIntercites
            (Boolean) data[9], // siLibreServiceAssiste
            (Boolean) data[10], // siOuverte
            (Boolean) data[11], // siPosteVenteGuichet
            (Integer) data[12], // annee
            (Integer) data[13], // nombreArret
            (Integer) data[14] // refTypeArretId

        );
    }
}