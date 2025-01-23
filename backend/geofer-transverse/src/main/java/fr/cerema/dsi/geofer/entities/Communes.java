package fr.cerema.dsi.geofer.entities;
 
import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;;
import org.locationtech.jts.geom.Geometry;
 
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "communes")
public class Communes extends GenericEntity {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 
  @Column(name = "aca_code")
  private String acaCode;
 
  @Column(name = "aca_id")
  private String acaId;
 
  @Column(name = "aca_nom")
  private String acaNom;
 
  @Column(name = "aca_paysage")
  private String acaPaysage;
 
  @Column(name = "aca_wikidata")
  private String acaWikidata;
 
  @Column(name = "atlas_id")
  private String atlasId;
 
  @Column(name = "atlas_nom")
  private String atlasNom;
 
  @Column(name = "atlas_paysage")
  private String atlasPaysage;
 
  @Column(name = "atlas_wikidata")
  private String atlasWikidata;
 
  @Column(name = "au_code")
  private String auCode;
 
  @Column(name = "au_id")
  private String auId;
 
  @Column(name = "auc_id")
  private String aucId;
 
  @Column(name = "auc_nom")
  private String aucNom;
 
  @Column(name = "com_code")
  private String comCode;
 
  @Column(name = "com_code1")
  private String comCode1;
 
  @Column(name = "com_code2")
  private String comCode2;
 
  @Column(name = "com_code_actuel")
  private String comCodeActuel;
 
  @Column(name = "com_code_actuel_depuis")
  private LocalDate comCodeActuelDepuis;
 
  @Column(name = "com_id")
  private String comId;
 
  @Column(name = "com_nom")
  private String comNom;
 
  @Column(name = "com_nom_anciens")
  private String comNomAnciens;
 
  @Column(name = "com_nom_anciens_dates")
  private String comNomAnciensDates;
 
  @Column(name = "com_nom_debut")
  private LocalDate comNomDebut;
 
  @Column(name = "com_nom_fin")
  private LocalDate comNomFin;
 
  @Column(name = "com_nom_maj")
  private String comNomMaj;
 
  @Column(name = "com_nom_maj_court")
  private String comNomMajCourt;
 
  @Column(name = "dep_code")
  private String depCode;
 
  @Column(name = "dep_id")
  private String depId;
 
  @Column(name = "dep_nom")
  private String depNom;
 
  @Column(name = "dep_nom_num")
  private String depNomNum;
 
  @Column(name = "dep_num_nom")
  private String depNumNom;
 
  @Column(name = "dep_paysage")
  private String depPaysage;
 
  @Column(name = "dep_wikidata")
  private String depWikidata;
 
  @Column(name = "epci_id")
  private String epciId;
 
  @Column(name = "epci_nom")
  private String epciNom;
 
  @Column(name = "fd_id")
  private String fdId;
 
  @Column(name = "fe_id")
  private String feId;
 
  @Column(name = "fr_id")
  private String frId;
 
  @Column(name = "geom")
  private Geometry geom;
 
  @Column(name = "geometry")
  private Geometry geometry;
 
  @Column(name = "nuts_code_1")
  private String nutsCode1;
 
  @Column(name = "nuts_code_3")
  private String nutsCode3;
 
  @Column(name = "reg_code")
  private String regCode;
 
  @Column(name = "reg_code_old")
  private String regCodeOld;
 
  @Column(name = "reg_id")
  private String regId;
 
  @Column(name = "reg_id_old")
  private String regIdOld;
 
  @Column(name = "reg_nom")
  private String regNom;
 
  @Column(name = "reg_nom_old")
  private String regNomOld;
 
  @Column(name = "reg_paysage")
  private String regPaysage;
 
  @Column(name = "reg_wikidata")
  private String regWikidata;
 
  @Column(name = "regrgp_nom")
  private String regrgpNom;
 
  @Column(name = "uu_code")
  private String uuCode;
 
  @Column(name = "uu_id")
  private String uuId;
 
  @Column(name = "uu_id_10")
  private String uuId10;
 
  @Column(name = "uu_id_99")
  private String uuId99;
 
  @Column(name = "uu_paysage")
  private String uuPaysage;
 
  @Column(name = "uu_wikidata")
  private String uuWikidata;
 
  @Column(name = "uucr_id")
  private String uucrId;
 
  @Column(name = "uucr_nom")
  private String uucrNom;
 
  @Column(name = "ze_id")
  private String zeId;
 
}