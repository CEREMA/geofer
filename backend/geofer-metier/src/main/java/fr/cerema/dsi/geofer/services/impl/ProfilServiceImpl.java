package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.commons.services.GenericServiceImpl;
import fr.cerema.dsi.geofer.entities.Profil;
import fr.cerema.dsi.geofer.entities.Role;
import fr.cerema.dsi.geofer.repositories.ProfilRepository;
import fr.cerema.dsi.geofer.repositories.RoleRepository; 
import fr.cerema.dsi.geofer.services.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("profilService")
public class ProfilServiceImpl extends GenericServiceImpl<Profil, Long> implements ProfilService {

    private final ProfilRepository profilRepository;
    private final RoleRepository roleRepository; // Ajoutez cette ligne

    // Injectez RoleRepository via le constructeur
    public ProfilServiceImpl(ProfilRepository profilRepository, RoleRepository roleRepository) {
        this.profilRepository = profilRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllCurrentRolesByUsername(String username) {
        return this.profilRepository.findAllCurrentRoleByUsername(username);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).orElse(null); 
    }
}
