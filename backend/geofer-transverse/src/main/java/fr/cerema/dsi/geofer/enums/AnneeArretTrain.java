package fr.cerema.dsi.geofer.enums;
 
public enum AnneeArretTrain {
  AVANT_2000("Avant 2000"),
  ENTRE_2000_ET_2005("Entre 2000 et 2005"),
  ENTRE_2005_ET_2010("Entre 2005 et 2010"),
  ENTRE_2010_ET_2014("Entre 2010 et 2014"),
  JE_NE_SAIS_PAS("Je ne sais pas");
  private AnneeArretTrain(String description) {
    this.description = description;
  }
  private final String description;
  public String getDescription() {
    return description;
  }
}