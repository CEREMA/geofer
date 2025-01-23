package fr.cerema.dsi.geofer.enums;
 
public enum Flux {
  EXPEDIEE("expediee"),
  RECUE("recue");
  private Flux(String description) {
    this.description = description;
  }
  private final String description;
  public String getDescription() {
    return description;
  }
}