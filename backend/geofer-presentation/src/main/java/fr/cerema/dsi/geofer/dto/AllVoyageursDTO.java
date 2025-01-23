package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AllVoyageursDTO {
    private Integer annee;
    private Long totalParAnnee;
}