package fr.cerema.dsi.geofer.enums;
 
public enum FrequenceCirculationTrain {
  MOINS_DUN_TRAIN_PAR_AN_OU_JUSTE_UN_TRAIN_PAR_AN("Moins d'un train par an ou juste un train par an"),
  MOINS_DUN_TRAIN_PAR_JOUR_MAIS_PLUS_DUN_TRAIN_PAR_SEMAINE("Moins d'un train par jour mais plus d'un train par semaine"),
  MOINS_DUN_TRAIN_PAR_MOIS_MAIS_PLUS_DUN_TRAIN_PAR_AN("Moins d'un train par mois mais plus d'un train par an"),
  MOINS_DUN_TRAIN_PAR_SEMAINE_MAIS_PLUS_DUN_TRAIN_PAR_MOIS("Moins d'un train par semaine mais plus d'un train par mois"),
  PLUS_DUN_TRAIN_PAR_JOUR("Plus d'un train par jour"),
  UN_TRAIN_PAR_JOUR("Un train par jour"),
  UN_TRAIN_PAR_MOIS("Un train par mois"),
  UN_TRAIN_PAR_SEMAINE("Un train par semaine");
  private FrequenceCirculationTrain(String description) {
    this.description = description;
  }
  private final String description;
  public String getDescription() {
    return description;
  }
}