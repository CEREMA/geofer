package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import fr.cerema.dsi.geofer.entities.Profil;
import fr.cerema.dsi.geofer.entities.Role; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends GenericRepository<Role, Long> {

  Optional<Role> findByName(String name);

}

