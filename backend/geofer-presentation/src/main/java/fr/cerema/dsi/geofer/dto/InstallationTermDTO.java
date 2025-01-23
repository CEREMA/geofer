package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.InstallationTerm;
import fr.cerema.dsi.geofer.enums.AnneeArretTrain;
import java.time.LocalDate;;
import fr.cerema.dsi.geofer.enums.EtatIte;
import fr.cerema.dsi.geofer.enums.EvolutionIte;
import fr.cerema.dsi.geofer.enums.FrequenceCirculationTrain;
import fr.cerema.dsi.geofer.enums.LocoTracteur;
import fr.cerema.dsi.geofer.dto.RefActivitePrincipaleDTO;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

public class InstallationTermDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("anneeArretTrain")
  private String anneeArretTrain;
 
  @Getter
  @Setter
  @JsonProperty("codeIte")
  private Integer codeIte;
 
  @Getter
  @Setter
  @JsonProperty("dateEnquete")
  private LocalDate dateEnquete;
 
  @Getter
  @Setter
  @JsonProperty("entrepriseFerroviaire")
  private String entrepriseFerroviaire;
 
  @Getter
  @Setter
  @JsonProperty("etatIte")
  private String etatIte;
 
  @Getter
  @Setter
  @JsonProperty("evolutionDepuis2010")
  private String evolutionDepuis2010;
 
  @Getter
  @Setter
  @JsonProperty("evolutionFuture")
  private String evolutionFuture;
 
  @Getter
  @Setter
  @JsonProperty("frequenceCirculationTrain")
  private String frequenceCirculationTrain;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("locoTracteurRail")
  private String locoTracteurRail;
 
  @Getter
  @Setter
  @JsonProperty("locoTracteurRailRoute")
  private String locoTracteurRailRoute;
 
  @Getter
  @Setter
  @JsonProperty("longueurVoie")
  private Integer longueurVoie;
 
  @Getter
  @Setter
  @JsonProperty("nombreVoies")
  private Integer nombreVoies;
 
  @Getter
  @Setter
  @JsonProperty("raisonSociale")
  private String raisonSociale;
 
  @Getter
  @Setter
  @JsonProperty("refActivitePrincipale")
  private RefActivitePrincipaleDTO refActivitePrincipale;
 
  @Getter
  @Setter
  @JsonProperty("siRegimeLotissement")
  private Boolean siRegimeLotissement;
 
  @Getter
  @Setter
  @JsonProperty("siRegimeTrainEntier")
  private Boolean siRegimeTrainEntier;
 
  @Getter
  @Setter
  @JsonProperty("siReutilisationPrevue")
  private Boolean siReutilisationPrevue;
 
  @Getter
  @Setter
  @JsonProperty("siTrainEntierPossible")
  private Boolean siTrainEntierPossible;
 
  @Getter
  @Setter
  @JsonProperty("siUtilisee")
  private Boolean siUtilisee;
 
  @Getter
  @Setter
  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(using = GeometryDeserializer.class)
  @JsonProperty("theGeom")
  private Geometry theGeom;
 

  // Default constructor
  public InstallationTermDTO() {}

  // Constructor with arguments
  public InstallationTermDTO(String anneeArretTrain, Integer codeIte, LocalDate dateEnquete, String entrepriseFerroviaire, String etatIte, String evolutionDepuis2010, String evolutionFuture, String frequenceCirculationTrain, Integer id, String locoTracteurRail, String locoTracteurRailRoute, Integer longueurVoie, Integer nombreVoies, String raisonSociale, RefActivitePrincipaleDTO refActivitePrincipale, Boolean siRegimeLotissement, Boolean siRegimeTrainEntier, Boolean siReutilisationPrevue, Boolean siTrainEntierPossible, Boolean siUtilisee, Geometry theGeom) {
    this.anneeArretTrain = anneeArretTrain;
    this.codeIte = codeIte;
    this.dateEnquete = dateEnquete;
    this.entrepriseFerroviaire = entrepriseFerroviaire;
    this.etatIte = etatIte;
    this.evolutionDepuis2010 = evolutionDepuis2010;
    this.evolutionFuture = evolutionFuture;
    this.frequenceCirculationTrain = frequenceCirculationTrain;
    this.id = id;
    this.locoTracteurRail = locoTracteurRail;
    this.locoTracteurRailRoute = locoTracteurRailRoute;
    this.longueurVoie = longueurVoie;
    this.nombreVoies = nombreVoies;
    this.raisonSociale = raisonSociale;
    this.refActivitePrincipale = refActivitePrincipale;
    this.siRegimeLotissement = siRegimeLotissement;
    this.siRegimeTrainEntier = siRegimeTrainEntier;
    this.siReutilisationPrevue = siReutilisationPrevue;
    this.siTrainEntierPossible = siTrainEntierPossible;
    this.siUtilisee = siUtilisee;
    this.theGeom = theGeom;
  }

  // setters & getters
  public String getAnneeArretTrain() {
    return anneeArretTrain;
  }
 
  public void setAnneeArretTrain(String anneeArretTrain) {
    this.anneeArretTrain = anneeArretTrain;
  }
 
  public Integer getCodeIte() {
    return codeIte;
  }
 
  public void setCodeIte(Integer codeIte) {
    this.codeIte = codeIte;
  }
 
  public LocalDate getDateEnquete() {
    return dateEnquete;
  }
 
  public void setDateEnquete(LocalDate dateEnquete) {
    this.dateEnquete = dateEnquete;
  }
 
  public String getEntrepriseFerroviaire() {
    return entrepriseFerroviaire;
  }
 
  public void setEntrepriseFerroviaire(String entrepriseFerroviaire) {
    this.entrepriseFerroviaire = entrepriseFerroviaire;
  }
 
  public String getEtatIte() {
    return etatIte;
  }
 
  public void setEtatIte(String etatIte) {
    this.etatIte = etatIte;
  }
 
  public String getEvolutionDepuis2010() {
    return evolutionDepuis2010;
  }
 
  public void setEvolutionDepuis2010(String evolutionDepuis2010) {
    this.evolutionDepuis2010 = evolutionDepuis2010;
  }
 
  public String getEvolutionFuture() {
    return evolutionFuture;
  }
 
  public void setEvolutionFuture(String evolutionFuture) {
    this.evolutionFuture = evolutionFuture;
  }
 
  public String getFrequenceCirculationTrain() {
    return frequenceCirculationTrain;
  }
 
  public void setFrequenceCirculationTrain(String frequenceCirculationTrain) {
    this.frequenceCirculationTrain = frequenceCirculationTrain;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public String getLocoTracteurRail() {
    return locoTracteurRail;
  }
 
  public void setLocoTracteurRail(String locoTracteurRail) {
    this.locoTracteurRail = locoTracteurRail;
  }
 
  public String getLocoTracteurRailRoute() {
    return locoTracteurRailRoute;
  }
 
  public void setLocoTracteurRailRoute(String locoTracteurRailRoute) {
    this.locoTracteurRailRoute = locoTracteurRailRoute;
  }
 
  public Integer getLongueurVoie() {
    return longueurVoie;
  }
 
  public void setLongueurVoie(Integer longueurVoie) {
    this.longueurVoie = longueurVoie;
  }
 
  public Integer getNombreVoies() {
    return nombreVoies;
  }
 
  public void setNombreVoies(Integer nombreVoies) {
    this.nombreVoies = nombreVoies;
  }
 
  public String getRaisonSociale() {
    return raisonSociale;
  }
 
  public void setRaisonSociale(String raisonSociale) {
    this.raisonSociale = raisonSociale;
  }
 
  public RefActivitePrincipaleDTO getRefActivitePrincipale() {
    return refActivitePrincipale;
  }
 
  public void setRefActivitePrincipale(RefActivitePrincipaleDTO refActivitePrincipale) {
    this.refActivitePrincipale = refActivitePrincipale;
  }
 
  public Boolean getSiRegimeLotissement() {
    return siRegimeLotissement;
  }
 
  public void setSiRegimeLotissement(Boolean siRegimeLotissement) {
    this.siRegimeLotissement = siRegimeLotissement;
  }
 
  public Boolean getSiRegimeTrainEntier() {
    return siRegimeTrainEntier;
  }
 
  public void setSiRegimeTrainEntier(Boolean siRegimeTrainEntier) {
    this.siRegimeTrainEntier = siRegimeTrainEntier;
  }
 
  public Boolean getSiReutilisationPrevue() {
    return siReutilisationPrevue;
  }
 
  public void setSiReutilisationPrevue(Boolean siReutilisationPrevue) {
    this.siReutilisationPrevue = siReutilisationPrevue;
  }
 
  public Boolean getSiTrainEntierPossible() {
    return siTrainEntierPossible;
  }
 
  public void setSiTrainEntierPossible(Boolean siTrainEntierPossible) {
    this.siTrainEntierPossible = siTrainEntierPossible;
  }
 
  public Boolean getSiUtilisee() {
    return siUtilisee;
  }
 
  public void setSiUtilisee(Boolean siUtilisee) {
    this.siUtilisee = siUtilisee;
  }
 
  public Geometry getTheGeom() {
    return theGeom;
  }
 
  public void setTheGeom(Geometry theGeom) {
    this.theGeom = theGeom;
  }
 
}