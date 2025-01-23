package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.RefClassePassageNiveau;

public class RefClassePassageNiveauDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("classe")
  private String classe;
 
  @Getter
  @Setter
  @JsonProperty("definition")
  private String definition;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 

  // Default constructor
  public RefClassePassageNiveauDTO() {}

  // Constructor with arguments
  public RefClassePassageNiveauDTO(String classe, String definition, Integer id) {
    this.classe = classe;
    this.definition = definition;
    this.id = id;
  }

  // setters & getters
  public String getClasse() {
    return classe;
  }
 
  public void setClasse(String classe) {
    this.classe = classe;
  }
 
  public String getDefinition() {
    return definition;
  }
 
  public void setDefinition(String definition) {
    this.definition = definition;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
}