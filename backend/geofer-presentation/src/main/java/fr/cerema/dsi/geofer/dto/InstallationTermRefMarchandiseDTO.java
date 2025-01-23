package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.InstallationTermRefMarchandise;
import fr.cerema.dsi.geofer.enums.Flux;
import fr.cerema.dsi.geofer.dto.InstallationTermDTO;
import fr.cerema.dsi.geofer.dto.RefMarchandiseDTO;

public class InstallationTermRefMarchandiseDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("flux")
  private String flux;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("installationTerm")
  private InstallationTermDTO installationTerm;
 
  @Getter
  @Setter
  @JsonProperty("refMarchandise")
  private RefMarchandiseDTO refMarchandise;
 
  @Getter
  @Setter
  @JsonProperty("siParTrain")
  private Boolean siParTrain;
 

  // Default constructor
  public InstallationTermRefMarchandiseDTO() {}

  // Constructor with arguments
  public InstallationTermRefMarchandiseDTO(String flux, Integer id, InstallationTermDTO installationTerm, RefMarchandiseDTO refMarchandise, Boolean siParTrain) {
    this.flux = flux;
    this.id = id;
    this.installationTerm = installationTerm;
    this.refMarchandise = refMarchandise;
    this.siParTrain = siParTrain;
  }

  // setters & getters
  public String getFlux() {
    return flux;
  }
 
  public void setFlux(String flux) {
    this.flux = flux;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public InstallationTermDTO getInstallationTerm() {
    return installationTerm;
  }
 
  public void setInstallationTerm(InstallationTermDTO installationTerm) {
    this.installationTerm = installationTerm;
  }
 
  public RefMarchandiseDTO getRefMarchandise() {
    return refMarchandise;
  }
 
  public void setRefMarchandise(RefMarchandiseDTO refMarchandise) {
    this.refMarchandise = refMarchandise;
  }
 
  public Boolean getSiParTrain() {
    return siParTrain;
  }
 
  public void setSiParTrain(Boolean siParTrain) {
    this.siParTrain = siParTrain;
  }
 
}