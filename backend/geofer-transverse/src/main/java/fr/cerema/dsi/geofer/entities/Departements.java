package fr.cerema.dsi.geofer.entities;
 
import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import org.locationtech.jts.geom.Geometry;
 
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "departements")
public class Departements extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @NotNull
  @Column(name = "code")
  private String code;
 
  @NotNull
  @Column(name = "geom")
  private Geometry geom;
 
  @NotNull
  @Column(name = "nom")
  private String nom;
 
}