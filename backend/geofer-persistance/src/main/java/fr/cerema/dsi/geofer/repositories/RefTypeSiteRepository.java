package fr.cerema.dsi.geofer.repositories;
 
import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.RefTypeSite;
 
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface RefTypeSiteRepository extends GenericRepository<RefTypeSite, Integer> {
}