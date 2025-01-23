package fr.cerema.dsi.geofer.enums;
 
public enum LocoTracteur {
  N_1("1"),
  N_2("2"),
  AUCUN("Aucun"),
  PLUS_DE_2("Plus de 2");
  private LocoTracteur(String description) {
    this.description = description;
  }
  private final String description;
  public String getDescription() {
    return description;
  }
}