package fr.cerema.dsi.geofer.dto;

import java.util.Set;

public class UserDTO extends GenericDTO {

  private String email;

  private String orionId;

  private Set<ProfilDTO> profilSet;

  private Long idDirectionCourante;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getOrionId() {
    return orionId;
  }

  public void setOrionId(String orionId) {
    this.orionId = orionId;
  }

  public Set<ProfilDTO> getProfilSet() {
    return profilSet;
  }

  public void setProfilSet(Set<ProfilDTO> profilSet) {
    this.profilSet = profilSet;
  }

  public Long getIdDirectionCourante() {
    return idDirectionCourante;
  }

  public void setIdDirectionCourante(Long idDirectionCourante) {
    this.idDirectionCourante = idDirectionCourante;
  }


  @Override
  public String toString() {
    return "UserDTO{" +
      "email='" + email + '\'' +
      ", orionId='" + orionId + '\'' +
      ", profilSet=" + profilSet +
      ", idDirectionCourante=" + idDirectionCourante +
      '}';
  }
}
