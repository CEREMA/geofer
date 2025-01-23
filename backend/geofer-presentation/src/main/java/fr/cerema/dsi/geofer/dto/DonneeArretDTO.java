package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.DonneeArret;
import fr.cerema.dsi.geofer.dto.GareDTO;
import fr.cerema.dsi.geofer.dto.RefTypeArretDTO;

public class DonneeArretDTO implements Serializable {

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
  @JsonProperty("nombreArret")
  private Integer nombreArret;
 
  @Getter
  @Setter
  @JsonProperty("refTypeArret")
  private RefTypeArretDTO refTypeArret;
 

  // Default constructor
  public DonneeArretDTO() {}

  // Constructor with arguments
  public DonneeArretDTO(Integer annee, GareDTO gare, Integer id, Integer nombreArret, RefTypeArretDTO refTypeArret) {
    this.annee = annee;
    this.gare = gare;
    this.id = id;
    this.nombreArret = nombreArret;
    this.refTypeArret = refTypeArret;
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
 
  public Integer getNombreArret() {
    return nombreArret;
  }
 
  public void setNombreArret(Integer nombreArret) {
    this.nombreArret = nombreArret;
  }
 
  public RefTypeArretDTO getRefTypeArret() {
    return refTypeArret;
  }
 
  public void setRefTypeArret(RefTypeArretDTO refTypeArret) {
    this.refTypeArret = refTypeArret;
  }
 
}