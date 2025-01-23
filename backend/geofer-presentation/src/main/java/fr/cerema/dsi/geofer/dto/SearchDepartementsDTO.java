package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class SearchDepartementsDTO {
    private String code;
    private String nom;
    private Object geom;
}