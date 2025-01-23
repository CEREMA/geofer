package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.Regions;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

public class RegionsDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("code")
  private String code;
 
  @Getter
  @Setter
  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(using = GeometryDeserializer.class)
  @JsonProperty("geom")
  private Geometry geom;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("nom")
  private String nom;
 

  // Default constructor
  public RegionsDTO() {}

  // Constructor with arguments
  public RegionsDTO(String code, Geometry geom, Integer id, String nom) {
    this.code = code;
    this.geom = geom;
    this.id = id;
    this.nom = nom;
  }

  // setters & getters
  public String getCode() {
    return code;
  }
 
  public void setCode(String code) {
    this.code = code;
  }
 
  public Geometry getGeom() {
    return geom;
  }
 
  public void setGeom(Geometry geom) {
    this.geom = geom;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public String getNom() {
    return nom;
  }
 
  public void setNom(String nom) {
    this.nom = nom;
  }
 
}