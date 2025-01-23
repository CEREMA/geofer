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
@Table(name = "isochrone")
public class Isochrone extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "gare_id", referencedColumnName = "id")
  @NotNull
  private Gare gare;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ref_type_isochrone_id", referencedColumnName = "id")
  @NotNull
  private RefTypeIsochrone refTypeIsochrone;
 
  @Column(name = "the_geom")
  private Geometry theGeom;
 
}