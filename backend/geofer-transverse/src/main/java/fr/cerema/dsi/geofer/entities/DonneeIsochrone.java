package fr.cerema.dsi.geofer.entities;
 
import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
 
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "donnee_isochrone")
public class DonneeIsochrone extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @NotNull
  @Column(name = "annee")
  private Integer annee;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "isochrone_id", referencedColumnName = "id")
  @NotNull
  private Isochrone isochrone;
 
  @Column(name = "nombre")
  private Integer nombre;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ref_type_donnee_id", referencedColumnName = "id")
  @NotNull
  private RefTypeDonnee refTypeDonnee;
 
}