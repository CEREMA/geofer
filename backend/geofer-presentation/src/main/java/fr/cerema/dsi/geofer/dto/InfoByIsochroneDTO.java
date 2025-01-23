package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class InfoByIsochroneDTO {
    private Integer gareId;
    private Integer nombre;
    private Integer annee;
    private String typeDonneeCamelcase;
    private String nommageCamelcase;
}