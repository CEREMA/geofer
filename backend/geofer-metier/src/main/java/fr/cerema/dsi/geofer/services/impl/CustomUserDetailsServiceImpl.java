package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.Profil;
import fr.cerema.dsi.geofer.entities.User;
import fr.cerema.dsi.geofer.entities.Role;
import fr.cerema.dsi.geofer.exceptions.security.NoRoleForUserServiceException;
import fr.cerema.dsi.geofer.repositories.ProfilRepository;
import fr.cerema.dsi.geofer.services.CustomUserDetailsService;
import fr.cerema.dsi.geofer.services.ProfilService;
import fr.cerema.dsi.geofer.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

  @Autowired
  private ProfilService profilService;

  @Autowired
  private UserService userService;

  @Autowired
  private ProfilRepository profilRepository; // Injection du ProfilRepository

  private static final Logger log = LoggerFactory.getLogger(UserDetails.class);

  @Override
  public UserDetails loadUserByUsername(String username) {
    // récupération des rôles
    List<Role> roles = profilService.findAllCurrentRolesByUsername(username);
    List<GrantedAuthority> authorities = new ArrayList<>();
    
    // Création des authorities de Spring
    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
    
    // Si l'utilisateur n'a pas de rôles, attribuez-lui le rôle 'GUEST'
    if (authorities.isEmpty()) {
        log.debug("Aucun rôle trouvé pour {}. Attribution du rôle GUEST par défaut.", username);
        Role guestRole = profilService.findRoleByName("GUEST");
        if (guestRole != null) {
            authorities.add(new SimpleGrantedAuthority(guestRole.getName()));
        } else {
            log.error("Le rôle GUEST est introuvable dans la base de données.");
            throw new NoRoleForUserServiceException(
                String.format("Impossible d'attribuer le rôle GUEST à l'utilisateur %s car il est introuvable dans la base de données.", username));
        }
    }

    return new org.springframework.security.core.userdetails.User(username, "", authorities);
  }


  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public UserDetails loadAndMajInfoUser(String orionId, String mail) {
    

    User user = userService.findByOrionId(orionId).orElse(null);


    if (user == null) {
        user = userService.findByEmail(mail).orElse(null);
    }

    if (user == null) {
      user = new User();
      user.setOrionId(orionId);
      user.setEmail(mail);
      user = userService.saveUser(user);
      
      Role guestRole = profilService.findRoleByName("GUEST");
      if (guestRole != null) {
          Profil guestProfil = new Profil();
          guestProfil.setUser(user);
          guestProfil.setRole(guestRole);
          // Sauvegardez d'abord le Profil
          guestProfil = profilService.save(guestProfil); 
          user.getProfilSet().add(guestProfil);
      }
      //userService.saveUser(user);
  }
   else {
        if (!StringUtils.equalsIgnoreCase(user.getOrionId(), orionId)) {
            user.setOrionId(orionId);
            userService.saveUser(user);
        }

        if (!StringUtils.equalsIgnoreCase(user.getEmail(), mail)) {
            user.setEmail(mail);
            userService.saveUser(user);
        }
    }

    return loadUserByUsername(user.getEmail());

  }
}
