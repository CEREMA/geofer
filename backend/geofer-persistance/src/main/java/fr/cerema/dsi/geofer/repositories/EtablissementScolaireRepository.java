package fr.cerema.dsi.geofer.repositories;
 
import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.EtablissementScolaire;
import java.util.List;
import fr.cerema.dsi.geofer.entities.RefTypeEtablissement;
 
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface EtablissementScolaireRepository extends GenericRepository<EtablissementScolaire, Integer> {
 
  List<EtablissementScolaire> findByRefTypeEtablissement(RefTypeEtablissement refTypeEtablissement);
 
}