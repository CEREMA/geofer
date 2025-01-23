package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.RefActivitePrincipale;

public class RefActivitePrincipaleDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("codeNaf")
  private String codeNaf;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("libelle")
  private String libelle;
 

  // Default constructor
  public RefActivitePrincipaleDTO() {}

  // Constructor with arguments
  public RefActivitePrincipaleDTO(String codeNaf, Integer id, String libelle) {
    this.codeNaf = codeNaf;
    this.id = id;
    this.libelle = libelle;
  }

  // setters & getters
  public String getCodeNaf() {
    return codeNaf;
  }
 
  public void setCodeNaf(String codeNaf) {
    this.codeNaf = codeNaf;
  }
 
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