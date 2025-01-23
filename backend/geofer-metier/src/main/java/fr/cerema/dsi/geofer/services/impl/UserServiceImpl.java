package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.commons.exceptions.UserNotFoundException;
import fr.cerema.dsi.commons.security.UserSecurityServiceUtil;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import fr.cerema.dsi.geofer.entities.User;
import fr.cerema.dsi.geofer.entities.Role;
import fr.cerema.dsi.geofer.repositories.UserRepository;
import fr.cerema.dsi.geofer.services.ProfilService;
import fr.cerema.dsi.geofer.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

  private final ProfilService profilService;

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository, ProfilService profilService) {
    this.userRepository = userRepository;
    this.profilService = profilService;
  }


  @Override
  public Optional<User> findByEmail(String email) {
    return this.userRepository.findByEmailIgnoreCase(email);
  }

  @Override
  public Optional<User> findByOrionId(String orionId) {
    return this.userRepository.findByOrionId(orionId);
  }

  @Override
  public void deleteUtilisateur(String mail) {
    Optional<User> user = this.findByEmail(mail);
    if (user.isPresent()) {
      this.deleteById(user.get().getId());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
        "L'utilisateur n'est pas présent dans la liste blanche");
    }
  }

  @Override
  public User saveUser(User user) {
    return this.userRepository.save(user);
  }

  @Override
  public Optional<User> getCurrentUser() {
    Optional<String> login = UserSecurityServiceUtil.getCurrentUserLogin();
    if (login.isPresent()) {
      return this.userRepository.findByEmailIgnoreCase(login.get());
    }
    throw new UserNotFoundException("Pas d'utilisateur authentifié trouvé");
  }




}
