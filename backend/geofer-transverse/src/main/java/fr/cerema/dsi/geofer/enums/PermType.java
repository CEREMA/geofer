package fr.cerema.dsi.geofer.enums;
 
public enum PermType {
  CONNECT("CONNECT"),
  CREATE("CREATE"),
  DELETE("DELETE"),
  EXECUTE("EXECUTE"),
  INSERT("INSERT"),
  REFERENCES("REFERENCES"),
  SELECT("SELECT"),
  TEMPORARY("TEMPORARY"),
  TRIGGER("TRIGGER"),
  TRUNCATE("TRUNCATE"),
  UPDATE("UPDATE"),
  USAGE("USAGE");
  private PermType(String description) {
    this.description = description;
  }
  private final String description;
  public String getDescription() {
    return description;
  }
}