package fr.cerema.dsi.geofer.enums;
 
public enum EvolutionIte {
  A_LA_BAISSE("A la baisse"),
  A_LA_HAUSSE("A la hausse"),
  DE_FAON_STABLE("De façon stable");
  private EvolutionIte(String description) {
    this.description = description;
  }
  private final String description;
  public String getDescription() {
    return description;
  }
}