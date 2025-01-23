package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.commons.services.GenericService;
import fr.cerema.dsi.geofer.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public interface UserService extends GenericService<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByOrionId(String orionId);

  /**
   * Supprime l'utilisateur correspondant au mail en base
   *
   * @param mail le mail de l'utilisateur
   */
  void deleteUtilisateur(String mail);

  User saveUser(User user);

  Optional<User> getCurrentUser();

}
