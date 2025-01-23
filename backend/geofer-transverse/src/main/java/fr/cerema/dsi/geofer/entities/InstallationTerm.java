package fr.cerema.dsi.geofer.entities;
 
import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;
import lombok.*;
import fr.cerema.dsi.geofer.enums.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;;
import org.locationtech.jts.geom.Geometry;
 
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "installation_term")
public class InstallationTerm extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @Column(name = "annee_arret_train")
  private String anneeArretTrain;
 
  @NotNull
  @Column(name = "code_ite")
  private Integer codeIte;
 
  @Column(name = "date_enquete")
  private LocalDate dateEnquete;
 
  @Column(name = "entreprise_ferroviaire")
  private String entrepriseFerroviaire;
 
  @Column(name = "etat_ite")
  private String etatIte;
 
  @Column(name = "evolution_depuis_2010")
  private String evolutionDepuis2010;
 
  @Column(name = "evolution_future")
  private String evolutionFuture;
 
  @Column(name = "frequence_circulation_train")
  private String frequenceCirculationTrain;
 
  @Column(name = "loco_tracteur_rail")
  private String locoTracteurRail;
 
  @Column(name = "loco_tracteur_rail_route")
  private String locoTracteurRailRoute;
 
  @Column(name = "longueur_voie")
  private Integer longueurVoie;
 
  @Column(name = "nombre_voies")
  private Integer nombreVoies;
 
  @Column(name = "raison_sociale")
  private String raisonSociale;
 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ref_activite_principale_id", referencedColumnName = "id")
  @NotNull
  private RefActivitePrincipale refActivitePrincipale;
 
  @Column(name = "si_regime_lotissement")
  private Boolean siRegimeLotissement;
 
  @Column(name = "si_regime_train_entier")
  private Boolean siRegimeTrainEntier;
 
  @Column(name = "si_reutilisation_prevue")
  private Boolean siReutilisationPrevue;
 
  @Column(name = "si_train_entier_possible")
  private Boolean siTrainEntierPossible;
 
  @Column(name = "si_utilisee")
  private Boolean siUtilisee;
 
  @Column(name = "the_geom")
  private Geometry theGeom;
 
}