package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.DonneeVoyageur;
import fr.cerema.dsi.geofer.dto.GareDTO;

public class DonneeVoyageurDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("annee")
  private Integer annee;
 
  @Getter
  @Setter
  @JsonProperty("gare")
  private GareDTO gare;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("nombreVoyageur")
  private Integer nombreVoyageur;
 

  // Default constructor
  public DonneeVoyageurDTO() {}

  // Constructor with arguments
  public DonneeVoyageurDTO(Integer annee, GareDTO gare, Integer id, Integer nombreVoyageur) {
    this.annee = annee;
    this.gare = gare;
    this.id = id;
    this.nombreVoyageur = nombreVoyageur;
  }

  // setters & getters
  public Integer getAnnee() {
    return annee;
  }
 
  public void setAnnee(Integer annee) {
    this.annee = annee;
  }
 
  public GareDTO getGare() {
    return gare;
  }
 
  public void setGare(GareDTO gare) {
    this.gare = gare;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public Integer getNombreVoyageur() {
    return nombreVoyageur;
  }
 
  public void setNombreVoyageur(Integer nombreVoyageur) {
    this.nombreVoyageur = nombreVoyageur;
  }
 
}