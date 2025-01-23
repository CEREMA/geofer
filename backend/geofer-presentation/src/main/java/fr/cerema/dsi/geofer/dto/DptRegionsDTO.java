package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.DptRegions;

public class DptRegionsDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("departmentCode")
  private String departmentCode;
 
  @Getter
  @Setter
  @JsonProperty("departmentName")
  private String departmentName;
 
  @Getter
  @Setter
  @JsonProperty("regionCode")
  private String regionCode;
 
  @Getter
  @Setter
  @JsonProperty("regionName")
  private String regionName;
 

  // Default constructor
  public DptRegionsDTO() {}

  // Constructor with arguments
  public DptRegionsDTO(String departmentCode, String departmentName, String regionCode, String regionName) {
    this.departmentCode = departmentCode;
    this.departmentName = departmentName;
    this.regionCode = regionCode;
    this.regionName = regionName;
  }

  // setters & getters
  public String getDepartmentCode() {
    return departmentCode;
  }
 
  public void setDepartmentCode(String departmentCode) {
    this.departmentCode = departmentCode;
  }
 
  public String getDepartmentName() {
    return departmentName;
  }
 
  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }
 
  public String getRegionCode() {
    return regionCode;
  }
 
  public void setRegionCode(String regionCode) {
    this.regionCode = regionCode;
  }
 
  public String getRegionName() {
    return regionName;
  }
 
  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }
 
}