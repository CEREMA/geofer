package fr.cerema.dsi.geofer.mapper;
import fr.cerema.dsi.geofer.dto.SearchDTO;
import org.springframework.stereotype.Component;


@Component
public class SearchMapper {
    public SearchDTO toDTO(Object[] data) {

        return new SearchDTO(
            (Integer) data[0], // id
            (Integer) data[1], // refMarchandiseId
            (Boolean) data[2], // siParTrain
            (String) data[3], // flux
            (String) data[4], // codeNst
            (String) data[5] // libelle

        );
    }
}