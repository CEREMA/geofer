package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.RefTypeIsochrone;

public class RefTypeIsochroneDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("libelle")
  private String libelle;
 

  // Default constructor
  public RefTypeIsochroneDTO() {}

  // Constructor with arguments
  public RefTypeIsochroneDTO(Integer id, String libelle) {
    this.id = id;
    this.libelle = libelle;
  }

  // setters & getters
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public String getLibelle() {
    return libelle;
  }
 
  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }
 
}