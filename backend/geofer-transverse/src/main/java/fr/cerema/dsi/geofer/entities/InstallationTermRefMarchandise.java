package fr.cerema.dsi.geofer.entities;
 
import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;
import lombok.*;
import fr.cerema.dsi.geofer.enums.*;
import jakarta.validation.constraints.NotNull;
 
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "installation_term_ref_marchandise")
public class InstallationTermRefMarchandise extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @NotNull
  @Column(name = "flux")
  private String flux;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "installation_term_id", referencedColumnName = "id")
  @NotNull
  private InstallationTerm installationTerm;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ref_marchandise_id", referencedColumnName = "id")
  @NotNull
  private RefMarchandise refMarchandise;
 
  @NotNull
  @Column(name = "si_par_train")
  private Boolean siParTrain;
 
}