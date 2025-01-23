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
@Table(name = "ref_marchandise")
public class RefMarchandise extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @NotNull
  @Column(name = "code_nst")
  private String codeNst;
 
  @NotNull
  @Column(name = "libelle")
  private String libelle;
 
}