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
@Table(name = "passage_niveau")
public class PassageNiveau extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @Column(name = "code_ligne")
  private String codeLigne;
 
  @Column(name = "id_pn")
  private Long idPn;
 
  @Column(name = "libelle")
  private String libelle;
 
  @Column(name = "obstacle")
  private String obstacle;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ref_classe_passage_niveau_id", referencedColumnName = "id")
  @NotNull
  private RefClassePassageNiveau refClassePassageNiveau;
 
  @Column(name = "the_geom")
  private Geometry theGeom;
 
}