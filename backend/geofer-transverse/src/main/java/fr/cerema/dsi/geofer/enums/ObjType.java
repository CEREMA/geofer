package fr.cerema.dsi.geofer.enums;
 
public enum ObjType {
  COLUMN("COLUMN"),
  DATABASE("DATABASE"),
  FUNCTION("FUNCTION"),
  SCHEMA("SCHEMA"),
  SEQUENCE("SEQUENCE"),
  TABLE("TABLE"),
  VIEW("VIEW");
  private ObjType(String description) {
    this.description = description;
  }
  private final String description;
  public String getDescription() {
    return description;
  }
}