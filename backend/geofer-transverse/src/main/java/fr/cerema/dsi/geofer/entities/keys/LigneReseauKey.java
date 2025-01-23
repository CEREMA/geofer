package fr.cerema.dsi.geofer.entities.keys;
 
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
 
@Embeddable
public class LigneReseauKey implements Serializable {
 
  @Column(name = "ligne_id")
  private Integer ligneId;
  public Integer getLigneId() {
    return ligneId;
  }
  public void setLigneId(Integer ligneId) {
    this.ligneId = ligneId;
  }
 
  @Column(name = "reseau_id")
  private Integer reseauId;
  public Integer getReseauId() {
    return reseauId;
  }
  public void setReseauId(Integer reseauId) {
    this.reseauId = reseauId;
  }
 
  public LigneReseauKey() {}
 
}