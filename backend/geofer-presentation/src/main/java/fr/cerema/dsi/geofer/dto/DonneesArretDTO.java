package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class DonneesArretDTO {
    private Integer annee;
    private Integer refTypeArret;
    private String libelle;
    private Integer nombreArret;
    private Integer gareId;
    private Integer id;
    private String nomGare;
}