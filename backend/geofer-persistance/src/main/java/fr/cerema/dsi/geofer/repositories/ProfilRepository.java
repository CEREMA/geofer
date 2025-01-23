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
public interface ProfilRepository extends GenericRepository<Profil, Long> {

  @Query("select p from Profil p where p.role = :role")
  Optional<Profil> findByRole(@Param("role") Role role);

  @Query(value = "SELECT r.name FROM {h-schema}roles AS r JOIN {h-schema}profil AS p ON p.role_id = r.id JOIN {h-schema}users AS u ON p.utilisateurid = u.id WHERE LOWER(u.email) = LOWER(:username)", nativeQuery = true)
  List<Role> findAllCurrentRoleByUsername(@Param("username") String username);

}
