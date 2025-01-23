package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.ReseauFerroviaire;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

public class ReseauFerroviaireDTO implements Serializable {

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
  @JsonProperty("idTypologiePetiteLigne")
  private Integer idTypologiePetiteLigne;
 
  @Getter
  @Setter
  @JsonProperty("infrastructure")
  private String infrastructure;
 
  @Getter
  @Setter
  @JsonProperty("mnemo")
  private String mnemo;
 
  @Getter
  @Setter
  @JsonProperty("pkDebutR")
  private String pkDebutR;
 
  @Getter
  @Setter
  @JsonProperty("pkFinR")
  private String pkFinR;
 
  @Getter
  @Setter
  @JsonProperty("retenuePetiteLigne")
  private String retenuePetiteLigne;
 
  @Getter
  @Setter
  @JsonProperty("rgTroncon")
  private String rgTroncon;
 
  @Getter
  @Setter
  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(using = GeometryDeserializer.class)
  @JsonProperty("theGeom")
  private Geometry theGeom;
 

  // Default constructor
  public ReseauFerroviaireDTO() {}

  // Constructor with arguments
  public ReseauFerroviaireDTO(String codeLigne, Integer id, Integer idTypologiePetiteLigne, String infrastructure, String mnemo, String pkDebutR, String pkFinR, String retenuePetiteLigne, String rgTroncon, Geometry theGeom) {
    this.codeLigne = codeLigne;
    this.id = id;
    this.idTypologiePetiteLigne = idTypologiePetiteLigne;
    this.infrastructure = infrastructure;
    this.mnemo = mnemo;
    this.pkDebutR = pkDebutR;
    this.pkFinR = pkFinR;
    this.retenuePetiteLigne = retenuePetiteLigne;
    this.rgTroncon = rgTroncon;
    this.theGeom = theGeom;
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
 
  public Integer getIdTypologiePetiteLigne() {
    return idTypologiePetiteLigne;
  }
 
  public void setIdTypologiePetiteLigne(Integer idTypologiePetiteLigne) {
    this.idTypologiePetiteLigne = idTypologiePetiteLigne;
  }
 
  public String getInfrastructure() {
    return infrastructure;
  }
 
  public void setInfrastructure(String infrastructure) {
    this.infrastructure = infrastructure;
  }
 
  public String getMnemo() {
    return mnemo;
  }
 
  public void setMnemo(String mnemo) {
    this.mnemo = mnemo;
  }
 
  public String getPkDebutR() {
    return pkDebutR;
  }
 
  public void setPkDebutR(String pkDebutR) {
    this.pkDebutR = pkDebutR;
  }
 
  public String getPkFinR() {
    return pkFinR;
  }
 
  public void setPkFinR(String pkFinR) {
    this.pkFinR = pkFinR;
  }
 
  public String getRetenuePetiteLigne() {
    return retenuePetiteLigne;
  }
 
  public void setRetenuePetiteLigne(String retenuePetiteLigne) {
    this.retenuePetiteLigne = retenuePetiteLigne;
  }
 
  public String getRgTroncon() {
    return rgTroncon;
  }
 
  public void setRgTroncon(String rgTroncon) {
    this.rgTroncon = rgTroncon;
  }
 
  public Geometry getTheGeom() {
    return theGeom;
  }
 
  public void setTheGeom(Geometry theGeom) {
    this.theGeom = theGeom;
  }
 
}