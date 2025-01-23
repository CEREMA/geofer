package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.Isochrone;
import fr.cerema.dsi.geofer.dto.GareDTO;
import fr.cerema.dsi.geofer.dto.RefTypeIsochroneDTO;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

public class IsochroneDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("gare")
  private GareDTO gare;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("refTypeIsochrone")
  private RefTypeIsochroneDTO refTypeIsochrone;
 
  @Getter
  @Setter
  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(using = GeometryDeserializer.class)
  @JsonProperty("theGeom")
  private Geometry theGeom;
 

  // Default constructor
  public IsochroneDTO() {}

  // Constructor with arguments
  public IsochroneDTO(GareDTO gare, Integer id, RefTypeIsochroneDTO refTypeIsochrone, Geometry theGeom) {
    this.gare = gare;
    this.id = id;
    this.refTypeIsochrone = refTypeIsochrone;
    this.theGeom = theGeom;
  }

  // setters & getters
  public GareDTO getGare() {
    return gare;
  }
 
  public void setGare(GareDTO gare) {
    this.gare = gare;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public RefTypeIsochroneDTO getRefTypeIsochrone() {
    return refTypeIsochrone;
  }
 
  public void setRefTypeIsochrone(RefTypeIsochroneDTO refTypeIsochrone) {
    this.refTypeIsochrone = refTypeIsochrone;
  }
 
  public Geometry getTheGeom() {
    return theGeom;
  }
 
  public void setTheGeom(Geometry theGeom) {
    this.theGeom = theGeom;
  }
 
}