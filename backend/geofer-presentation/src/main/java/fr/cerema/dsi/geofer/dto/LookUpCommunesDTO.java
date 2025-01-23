package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class LookUpCommunesDTO {
    private Integer id;
    private String comNom;
    private String comCode;
}