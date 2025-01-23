package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.DonneeIsochrone;
import fr.cerema.dsi.geofer.dto.IsochroneDTO;
import fr.cerema.dsi.geofer.dto.RefTypeDonneeDTO;

public class DonneeIsochroneDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("annee")
  private Integer annee;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("isochrone")
  private IsochroneDTO isochrone;
 
  @Getter
  @Setter
  @JsonProperty("nombre")
  private Integer nombre;
 
  @Getter
  @Setter
  @JsonProperty("refTypeDonnee")
  private RefTypeDonneeDTO refTypeDonnee;
 

  // Default constructor
  public DonneeIsochroneDTO() {}

  // Constructor with arguments
  public DonneeIsochroneDTO(Integer annee, Integer id, IsochroneDTO isochrone, Integer nombre, RefTypeDonneeDTO refTypeDonnee) {
    this.annee = annee;
    this.id = id;
    this.isochrone = isochrone;
    this.nombre = nombre;
    this.refTypeDonnee = refTypeDonnee;
  }

  // setters & getters
  public Integer getAnnee() {
    return annee;
  }
 
  public void setAnnee(Integer annee) {
    this.annee = annee;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public IsochroneDTO getIsochrone() {
    return isochrone;
  }
 
  public void setIsochrone(IsochroneDTO isochrone) {
    this.isochrone = isochrone;
  }
 
  public Integer getNombre() {
    return nombre;
  }
 
  public void setNombre(Integer nombre) {
    this.nombre = nombre;
  }
 
  public RefTypeDonneeDTO getRefTypeDonnee() {
    return refTypeDonnee;
  }
 
  public void setRefTypeDonnee(RefTypeDonneeDTO refTypeDonnee) {
    this.refTypeDonnee = refTypeDonnee;
  }
 
}