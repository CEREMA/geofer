package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.Profil;
import fr.cerema.dsi.geofer.entities.Role;
import fr.cerema.dsi.commons.services.GenericService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("profilService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public interface ProfilService extends GenericService<Profil, Long> {

    List<Role> findAllCurrentRolesByUsername(String username);

    // Ajoutez cette ligne pour la nouvelle méthode
    Role findRoleByName(String name);
}
