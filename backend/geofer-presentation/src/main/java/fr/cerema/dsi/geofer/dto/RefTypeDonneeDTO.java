package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.RefTypeDonnee;

public class RefTypeDonneeDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("groupeDonnee")
  private String groupeDonnee;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("typeDonnee")
  private String typeDonnee;
 

  // Default constructor
  public RefTypeDonneeDTO() {}

  // Constructor with arguments
  public RefTypeDonneeDTO(String groupeDonnee, Integer id, String typeDonnee) {
    this.groupeDonnee = groupeDonnee;
    this.id = id;
    this.typeDonnee = typeDonnee;
  }

  // setters & getters
  public String getGroupeDonnee() {
    return groupeDonnee;
  }
 
  public void setGroupeDonnee(String groupeDonnee) {
    this.groupeDonnee = groupeDonnee;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public String getTypeDonnee() {
    return typeDonnee;
  }
 
  public void setTypeDonnee(String typeDonnee) {
    this.typeDonnee = typeDonnee;
  }
 
}