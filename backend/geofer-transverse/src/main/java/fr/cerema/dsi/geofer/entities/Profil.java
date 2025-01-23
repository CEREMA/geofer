package fr.cerema.dsi.geofer.entities;

import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Cette classe correspond au profil d'un {@link User} pour une direction donnée.
 */
@Entity
@Table(name = "Profil")
@Getter
@Setter
public class Profil extends GenericEntity {

  @Id
  @SequenceGenerator(name = "profil_id_seq", sequenceName = "profil_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "profil_id_seq", strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "role_id")
  private Role role;

  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateurid")  // Remplacez 'utilisateurid' par le nom réel de la colonne de clé étrangère dans votre base de données.
    private User user;

    // Getters et setters pour 'user'
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profil profil = (Profil) o;
    return Objects.equals(role, profil.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role);
  }

  @Override
  public String toString() {
    return "Profil{" +
      "id=" + id +
      ", profilUtilisateur=" + (role != null ? role.getName() : "null") +
      '}';
  }
}
