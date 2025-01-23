package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class DeptByRegionDTO {
    private String departmentCode;
    private String departmentName;
    private String regionCode;
    private String regionName;
}