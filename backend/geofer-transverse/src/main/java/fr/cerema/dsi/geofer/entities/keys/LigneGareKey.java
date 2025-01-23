package fr.cerema.dsi.geofer.entities.keys;
 
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
 
@Embeddable
public class LigneGareKey implements Serializable {
 
  @Column(name = "gare_id")
  private Integer gareId;
  public Integer getGareId() {
    return gareId;
  }
  public void setGareId(Integer gareId) {
    this.gareId = gareId;
  }
 
  @Column(name = "ligne_id")
  private Integer ligneId;
  public Integer getLigneId() {
    return ligneId;
  }
  public void setLigneId(Integer ligneId) {
    this.ligneId = ligneId;
  }
 
  public LigneGareKey() {}
 
}