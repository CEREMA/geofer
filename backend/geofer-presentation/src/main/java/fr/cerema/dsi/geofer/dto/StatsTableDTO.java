package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class StatsTableDTO {
    private String nomGare;
    private String statutGare;
    private Integer nombreArretsTotal;
    private String nomAom;
    private Integer anneevoyageurs;
    private Integer anneearrets;
    private Integer voyageurs;
    private Integer gareId;
}