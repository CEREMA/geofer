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
@Table(name = "installation_term_ref_type_site")
public class InstallationTermRefTypeSite extends GenericEntity {
 
  @EmbeddedId
  private InstallationTermRefTypeSiteKey id;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "installation_term_id", insertable = false, updatable = false)
  @NotNull
  private InstallationTerm installationTerm;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ref_type_site_id", insertable = false, updatable = false)
  @NotNull
  private RefTypeSite refTypeSite;
 
}