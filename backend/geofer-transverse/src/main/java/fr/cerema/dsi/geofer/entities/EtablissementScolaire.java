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
@Table(name = "etablissement_scolaire")
public class EtablissementScolaire extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @Column(name = "annee")
  private Integer annee;
 
  @Column(name = "nb_eleve")
  private Integer nbEleve;
 
  @Column(name = "nom_etablissement")
  private String nomEtablissement;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ref_type_etablissement_id", referencedColumnName = "id")
  @NotNull
  private RefTypeEtablissement refTypeEtablissement;
 
  @Column(name = "the_geom")
  private Geometry theGeom;
 
}