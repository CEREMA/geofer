package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.PassageNiveau;
import fr.cerema.dsi.geofer.dto.RefClassePassageNiveauDTO;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

public class PassageNiveauDTO implements Serializable {

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
  @JsonProperty("idPn")
  private Long idPn;
 
  @Getter
  @Setter
  @JsonProperty("libelle")
  private String libelle;
 
  @Getter
  @Setter
  @JsonProperty("obstacle")
  private String obstacle;
 
  @Getter
  @Setter
  @JsonProperty("refClassePassageNiveau")
  private RefClassePassageNiveauDTO refClassePassageNiveau;
 
  @Getter
  @Setter
  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(using = GeometryDeserializer.class)
  @JsonProperty("theGeom")
  private Geometry theGeom;
 

  // Default constructor
  public PassageNiveauDTO() {}

  // Constructor with arguments
  public PassageNiveauDTO(String codeLigne, Integer id, Long idPn, String libelle, String obstacle, RefClassePassageNiveauDTO refClassePassageNiveau, Geometry theGeom) {
    this.codeLigne = codeLigne;
    this.id = id;
    this.idPn = idPn;
    this.libelle = libelle;
    this.obstacle = obstacle;
    this.refClassePassageNiveau = refClassePassageNiveau;
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
 
  public Long getIdPn() {
    return idPn;
  }
 
  public void setIdPn(Long idPn) {
    this.idPn = idPn;
  }
 
  public String getLibelle() {
    return libelle;
  }
 
  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }
 
  public String getObstacle() {
    return obstacle;
  }
 
  public void setObstacle(String obstacle) {
    this.obstacle = obstacle;
  }
 
  public RefClassePassageNiveauDTO getRefClassePassageNiveau() {
    return refClassePassageNiveau;
  }
 
  public void setRefClassePassageNiveau(RefClassePassageNiveauDTO refClassePassageNiveau) {
    this.refClassePassageNiveau = refClassePassageNiveau;
  }
 
  public Geometry getTheGeom() {
    return theGeom;
  }
 
  public void setTheGeom(Geometry theGeom) {
    this.theGeom = theGeom;
  }
 
}