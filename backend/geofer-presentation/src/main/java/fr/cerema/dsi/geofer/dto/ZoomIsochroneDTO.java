package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ZoomIsochroneDTO {
    private Integer id;
    private Integer refTypeIsochroneId;
    private Integer gareId;
    private Object theGeom;
}