package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.RefMarchandise;

public class RefMarchandiseDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("codeNst")
  private String codeNst;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("libelle")
  private String libelle;
 

  // Default constructor
  public RefMarchandiseDTO() {}

  // Constructor with arguments
  public RefMarchandiseDTO(String codeNst, Integer id, String libelle) {
    this.codeNst = codeNst;
    this.id = id;
    this.libelle = libelle;
  }

  // setters & getters
  public String getCodeNst() {
    return codeNst;
  }
 
  public void setCodeNst(String codeNst) {
    this.codeNst = codeNst;
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