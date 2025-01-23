package fr.cerema.dsi.geofer.dto;

import fr.cerema.dsi.geofer.entities.Role; // Changez l'importation pour utiliser l'entité

public class ProfilDTO extends GenericDTO {

  private Role profilUtilisateur; // Gardez le type mais changez la source

  public Role getProfilUtilisateur() {
    return profilUtilisateur;
  }

  public void setProfilUtilisateur(Role profilUtilisateur) {
    this.profilUtilisateur = profilUtilisateur;
  }

  @Override
  public String toString() {
    return "ProfilDTO{" +
      "id=" + this.getId() +
      ", profilUtilisateur=" + (profilUtilisateur != null ? profilUtilisateur.getName() : "null") +
      '}';
  }
}