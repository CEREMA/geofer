package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.EtablissementScolaire;
import fr.cerema.dsi.geofer.dto.RefTypeEtablissementDTO;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

public class EtablissementScolaireDTO implements Serializable {

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
  @JsonProperty("nbEleve")
  private Integer nbEleve;
 
  @Getter
  @Setter
  @JsonProperty("nomEtablissement")
  private String nomEtablissement;
 
  @Getter
  @Setter
  @JsonProperty("refTypeEtablissement")
  private RefTypeEtablissementDTO refTypeEtablissement;
 
  @Getter
  @Setter
  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(using = GeometryDeserializer.class)
  @JsonProperty("theGeom")
  private Geometry theGeom;
 

  // Default constructor
  public EtablissementScolaireDTO() {}

  // Constructor with arguments
  public EtablissementScolaireDTO(Integer annee, Integer id, Integer nbEleve, String nomEtablissement, RefTypeEtablissementDTO refTypeEtablissement, Geometry theGeom) {
    this.annee = annee;
    this.id = id;
    this.nbEleve = nbEleve;
    this.nomEtablissement = nomEtablissement;
    this.refTypeEtablissement = refTypeEtablissement;
    this.theGeom = theGeom;
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
 
  public Integer getNbEleve() {
    return nbEleve;
  }
 
  public void setNbEleve(Integer nbEleve) {
    this.nbEleve = nbEleve;
  }
 
  public String getNomEtablissement() {
    return nomEtablissement;
  }
 
  public void setNomEtablissement(String nomEtablissement) {
    this.nomEtablissement = nomEtablissement;
  }
 
  public RefTypeEtablissementDTO getRefTypeEtablissement() {
    return refTypeEtablissement;
  }
 
  public void setRefTypeEtablissement(RefTypeEtablissementDTO refTypeEtablissement) {
    this.refTypeEtablissement = refTypeEtablissement;
  }
 
  public Geometry getTheGeom() {
    return theGeom;
  }
 
  public void setTheGeom(Geometry theGeom) {
    this.theGeom = theGeom;
  }
 
}