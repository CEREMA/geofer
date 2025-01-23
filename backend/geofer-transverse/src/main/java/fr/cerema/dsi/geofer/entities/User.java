package fr.cerema.dsi.geofer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "users")
@Getter
@Setter
public class User extends GenericEntity {

  @Id
  @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "users_id_seq", strategy = GenerationType.SEQUENCE)
  @JsonProperty
  private Long id;

  @Column(nullable = false, unique = true)
  @JsonProperty
  private String email;

  @Column(name = "orionid")
  @JsonProperty
  private String orionId;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "utilisateurid")
  private Set<Profil> profilSet = new HashSet<>();

  public User() {
    super();
  }

  /**
   * Creates a new User instance using supplied login, email and password
   *
   * @param email the user email
   */
  public User(String email) {
    this.setEmail(email);
    this.profilSet = new HashSet<>();
  }

  /**
   * Creates a new User instance using supplied id, login, email and password
   *
   * @param id    the user id
   * @param email the user email
   */
  public User(Long id, String email) {
    this(email);
    this.setId(id);
  }

  /**
   * toString method implementation
   *
   * @return the String representation of the User instance
   */
  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", email='" + email + '\'' +
      ", orionId='" + orionId + '\'' +
      ", profilSet=" + profilSet +
      '}';
  }
}
