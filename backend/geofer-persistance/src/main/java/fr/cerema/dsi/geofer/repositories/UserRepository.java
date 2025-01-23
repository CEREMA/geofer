package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import fr.cerema.dsi.geofer.entities.User;
import fr.cerema.dsi.geofer.entities.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends GenericRepository<User, Long> {

  @EntityGraph(attributePaths = "profilSet")
  Optional<User> findByEmailIgnoreCase(String email);

  Optional<User> findByOrionId(String orionId);

  @Query(value = "SELECT DISTINCT u FROM User u JOIN u.profilSet p WHERE p.role = :role")
  List<User> findAllByRole(@Param("role") Role role);

}
