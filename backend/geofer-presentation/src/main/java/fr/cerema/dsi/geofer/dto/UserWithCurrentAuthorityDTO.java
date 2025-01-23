package fr.cerema.dsi.geofer.dto;

import fr.cerema.dsi.geofer.entities.Role; 
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserWithCurrentAuthorityDTO implements Serializable {

  private Long id;

  private String email;

  private String orionId;

  private List<String> roleActifList;


  @Override
  public String toString() {
    return "UserWithCurrentAuthorityDTO{" +
      "id=" + id +
      ", email='" + email + '\'' +
      ", orionId='" + orionId + '\'' +
      ", roleActifList=" + (roleActifList != null ? roleActifList : "null") +
      '}';
  }
}
