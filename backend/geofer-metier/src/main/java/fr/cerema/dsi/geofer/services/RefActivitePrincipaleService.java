package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.RefActivitePrincipale;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface RefActivitePrincipaleService extends GenericService<RefActivitePrincipale, Integer> {
 
  List<RefActivitePrincipale> getAll();
  RefActivitePrincipale getById(Integer id);
  RefActivitePrincipale save(RefActivitePrincipale refActivitePrincipale);
  void delete(Integer id);

}