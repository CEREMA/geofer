package fr.cerema.dsi.geofer.entities.keys;
 
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
 
@Embeddable
public class InstallationTermRefTypeSiteKey implements Serializable {
 
  @Column(name = "installation_term_id")
  private Integer installationTermId;
  public Integer getInstallationTermId() {
    return installationTermId;
  }
  public void setInstallationTermId(Integer installationTermId) {
    this.installationTermId = installationTermId;
  }
 
  @Column(name = "ref_type_site_id")
  private Integer refTypeSiteId;
  public Integer getRefTypeSiteId() {
    return refTypeSiteId;
  }
  public void setRefTypeSiteId(Integer refTypeSiteId) {
    this.refTypeSiteId = refTypeSiteId;
  }
 
  public InstallationTermRefTypeSiteKey() {}
 
}