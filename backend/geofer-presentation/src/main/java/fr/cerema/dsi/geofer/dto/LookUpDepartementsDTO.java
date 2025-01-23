package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class LookUpDepartementsDTO {
    private Integer id;
    private String code;
    private String nom;
}