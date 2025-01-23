package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.exceptions.security.NoRoleForUserServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface CustomUserDetailsService extends UserDetailsService {
  UserDetails loadUserByUsername(String username) throws NoRoleForUserServiceException;

  UserDetails loadAndMajInfoUser(String orionId, String mail);
}
