package fr.cerema.dsi.geofer.repositories;
 
import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.LigneReseau;
import java.util.List;
import fr.cerema.dsi.geofer.entities.Ligne;
import fr.cerema.dsi.geofer.entities.ReseauFerroviaire;
 
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface LigneReseauRepository extends GenericRepository<LigneReseau, Integer> {
 
  List<LigneReseau> findByLigne(Ligne ligne);
  List<LigneReseau> findByReseauFerroviaire(ReseauFerroviaire reseauFerroviaire);
 
}