package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.Gare;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

public class GareDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("codeUic")
  private Integer codeUic;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("inseeCommune")
  private String inseeCommune;
 
  @Getter
  @Setter
  @JsonProperty("inseeDepartement")
  private String inseeDepartement;
 
  @Getter
  @Setter
  @JsonProperty("nomAom")
  private String nomAom;
 
  @Getter
  @Setter
  @JsonProperty("nomCommune")
  private String nomCommune;
 
  @Getter
  @Setter
  @JsonProperty("nomGare")
  private String nomGare;
 
  @Getter
  @Setter
  @JsonProperty("siAutomatesTer")
  private Boolean siAutomatesTer;
 
  @Getter
  @Setter
  @JsonProperty("siAutomatesTgvIntercites")
  private Boolean siAutomatesTgvIntercites;
 
  @Getter
  @Setter
  @JsonProperty("siLibreServiceAssiste")
  private Boolean siLibreServiceAssiste;
 
  @Getter
  @Setter
  @JsonProperty("siOuverte")
  private Boolean siOuverte;
 
  @Getter
  @Setter
  @JsonProperty("siPosteVenteGuichet")
  private Boolean siPosteVenteGuichet;
 
  @Getter
  @Setter
  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(using = GeometryDeserializer.class)
  @JsonProperty("theGeom")
  private Geometry theGeom;
 

  // Default constructor
  public GareDTO() {}

  // Constructor with arguments
  public GareDTO(Integer codeUic, Integer id, String inseeCommune, String inseeDepartement, String nomAom, String nomCommune, String nomGare, Boolean siAutomatesTer, Boolean siAutomatesTgvIntercites, Boolean siLibreServiceAssiste, Boolean siOuverte, Boolean siPosteVenteGuichet, Geometry theGeom) {
    this.codeUic = codeUic;
    this.id = id;
    this.inseeCommune = inseeCommune;
    this.inseeDepartement = inseeDepartement;
    this.nomAom = nomAom;
    this.nomCommune = nomCommune;
    this.nomGare = nomGare;
    this.siAutomatesTer = siAutomatesTer;
    this.siAutomatesTgvIntercites = siAutomatesTgvIntercites;
    this.siLibreServiceAssiste = siLibreServiceAssiste;
    this.siOuverte = siOuverte;
    this.siPosteVenteGuichet = siPosteVenteGuichet;
    this.theGeom = theGeom;
  }

  // setters & getters
  public Integer getCodeUic() {
    return codeUic;
  }
 
  public void setCodeUic(Integer codeUic) {
    this.codeUic = codeUic;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public String getInseeCommune() {
    return inseeCommune;
  }
 
  public void setInseeCommune(String inseeCommune) {
    this.inseeCommune = inseeCommune;
  }
 
  public String getInseeDepartement() {
    return inseeDepartement;
  }
 
  public void setInseeDepartement(String inseeDepartement) {
    this.inseeDepartement = inseeDepartement;
  }
 
  public String getNomAom() {
    return nomAom;
  }
 
  public void setNomAom(String nomAom) {
    this.nomAom = nomAom;
  }
 
  public String getNomCommune() {
    return nomCommune;
  }
 
  public void setNomCommune(String nomCommune) {
    this.nomCommune = nomCommune;
  }
 
  public String getNomGare() {
    return nomGare;
  }
 
  public void setNomGare(String nomGare) {
    this.nomGare = nomGare;
  }
 
  public Boolean getSiAutomatesTer() {
    return siAutomatesTer;
  }
 
  public void setSiAutomatesTer(Boolean siAutomatesTer) {
    this.siAutomatesTer = siAutomatesTer;
  }
 
  public Boolean getSiAutomatesTgvIntercites() {
    return siAutomatesTgvIntercites;
  }
 
  public void setSiAutomatesTgvIntercites(Boolean siAutomatesTgvIntercites) {
    this.siAutomatesTgvIntercites = siAutomatesTgvIntercites;
  }
 
  public Boolean getSiLibreServiceAssiste() {
    return siLibreServiceAssiste;
  }
 
  public void setSiLibreServiceAssiste(Boolean siLibreServiceAssiste) {
    this.siLibreServiceAssiste = siLibreServiceAssiste;
  }
 
  public Boolean getSiOuverte() {
    return siOuverte;
  }
 
  public void setSiOuverte(Boolean siOuverte) {
    this.siOuverte = siOuverte;
  }
 
  public Boolean getSiPosteVenteGuichet() {
    return siPosteVenteGuichet;
  }
 
  public void setSiPosteVenteGuichet(Boolean siPosteVenteGuichet) {
    this.siPosteVenteGuichet = siPosteVenteGuichet;
  }
 
  public Geometry getTheGeom() {
    return theGeom;
  }
 
  public void setTheGeom(Geometry theGeom) {
    this.theGeom = theGeom;
  }
 
}