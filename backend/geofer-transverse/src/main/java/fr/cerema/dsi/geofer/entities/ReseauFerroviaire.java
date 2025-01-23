package fr.cerema.dsi.geofer.entities;
 
import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Geometry;
 
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "reseau_ferroviaire")
public class ReseauFerroviaire extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @Column(name = "code_ligne")
  private String codeLigne;
 
  @Column(name = "id_typologie_petite_ligne")
  private Integer idTypologiePetiteLigne;
 
  @Column(name = "infrastructure")
  private String infrastructure;
 
  @Column(name = "mnemo")
  private String mnemo;
 
  @Column(name = "pk_debut_r")
  private String pkDebutR;
 
  @Column(name = "pk_fin_r")
  private String pkFinR;
 
  @Column(name = "retenue_petite_ligne")
  private String retenuePetiteLigne;
 
  @Column(name = "rg_troncon")
  private String rgTroncon;
 
  @Column(name = "the_geom")
  private Geometry theGeom;
 
}