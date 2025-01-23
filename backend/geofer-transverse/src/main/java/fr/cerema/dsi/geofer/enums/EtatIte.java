package fr.cerema.dsi.geofer.enums;
 
public enum EtatIte {
  BON("Bon"),
  INUTILISABLE("Inutilisable"),
  MAUVAIS("Mauvais"),
  NEUF("Neuf"),
  UTILISE("Utilisée");
  private EtatIte(String description) {
    this.description = description;
  }
  private final String description;
  public String getDescription() {
    return description;
  }
}