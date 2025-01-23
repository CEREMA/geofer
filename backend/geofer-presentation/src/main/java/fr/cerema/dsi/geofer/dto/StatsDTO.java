package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class StatsDTO {
    private String typeDonnee;
    private Integer annee;
    private Integer nombre;
    private Integer gareId;
    private String groupeDonnee;
    private String libelleTypeIsochrone;
}