package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class FrequentationVoyageurDTO {
    private Integer gareId;
    private String nomGare;
    private Integer annee;
    private Integer nombreVoyageur;
    private Boolean siOuverte;
}