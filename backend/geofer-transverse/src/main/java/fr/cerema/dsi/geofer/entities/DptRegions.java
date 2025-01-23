package fr.cerema.dsi.geofer.entities;
 
import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;
import lombok.*;
 
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "dpt_regions")
public class DptRegions extends GenericEntity {
 
  @Id
  @Column(name = "department_code")
  private String department_code;
 
  @Column(name = "department_name")
  private String departmentName;
 
  @Column(name = "region_code")
  private String regionCode;
 
  @Column(name = "region_name")
  private String regionName;
 
}