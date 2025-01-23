package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class SearchDTO {
    private Integer id;
    private Integer refMarchandiseId;
    private Boolean siParTrain;
    private String flux;
    private String codeNst;
    private String libelle;
}