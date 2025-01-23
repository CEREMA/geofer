package fr.cerema.dsi.geofer.entities;
 
import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;
import lombok.*;
import fr.cerema.dsi.geofer.entities.keys.*;
import jakarta.validation.constraints.NotNull;
 
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "ligne_reseau")
public class LigneReseau extends GenericEntity {
 
  @EmbeddedId
  private LigneReseauKey id;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ligne_id", insertable = false, updatable = false)
  @NotNull
  private Ligne ligne;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "reseau_id", insertable = false, updatable = false)
  @NotNull
  private ReseauFerroviaire reseauFerroviaire;
 
}