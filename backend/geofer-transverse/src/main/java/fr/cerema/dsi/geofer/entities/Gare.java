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
@Table(name = "gare")
public class Gare extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @Column(name = "code_uic")
  private Integer codeUic;
 
  @Column(name = "insee_commune")
  private String inseeCommune;
 
  @Column(name = "insee_departement")
  private String inseeDepartement;
 
  @Column(name = "nom_aom")
  private String nomAom;
 
  @Column(name = "nom_commune")
  private String nomCommune;
 
  @Column(name = "nom_gare")
  private String nomGare;
 
  @Column(name = "si_automates_ter")
  private Boolean siAutomatesTer;
 
  @Column(name = "si_automates_tgv_intercites")
  private Boolean siAutomatesTgvIntercites;
 
  @Column(name = "si_libre_service_assiste")
  private Boolean siLibreServiceAssiste;
 
  @NotNull
  @Column(name = "si_ouverte")
  private Boolean siOuverte;
 
  @Column(name = "si_poste_vente_guichet")
  private Boolean siPosteVenteGuichet;
 
  @Column(name = "the_geom")
  private Geometry theGeom;
 
}