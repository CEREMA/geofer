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
@Table(name = "donnee_voyageur")
public class DonneeVoyageur extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @NotNull
  @Column(name = "annee")
  private Integer annee;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "gare_id", referencedColumnName = "id")
  @NotNull
  private Gare gare;
 
  @Column(name = "nombre_voyageur")
  private Integer nombreVoyageur;
 
}