package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class DonneeVoyageurByGareDTO {
    private Integer id;
    private Integer annee;
    private Integer nombreVoyageur;
    private Integer gareId;
}