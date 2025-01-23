package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.Ligne;

public class LigneDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("codeLigne")
  private String codeLigne;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("nomLigne")
  private String nomLigne;
 

  // Default constructor
  public LigneDTO() {}

  // Constructor with arguments
  public LigneDTO(String codeLigne, Integer id, String nomLigne) {
    this.codeLigne = codeLigne;
    this.id = id;
    this.nomLigne = nomLigne;
  }

  // setters & getters
  public String getCodeLigne() {
    return codeLigne;
  }
 
  public void setCodeLigne(String codeLigne) {
    this.codeLigne = codeLigne;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public String getNomLigne() {
    return nomLigne;
  }
 
  public void setNomLigne(String nomLigne) {
    this.nomLigne = nomLigne;
  }
 
}