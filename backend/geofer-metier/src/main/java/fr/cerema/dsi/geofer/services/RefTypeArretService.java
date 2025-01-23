package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.RefTypeArret;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface RefTypeArretService extends GenericService<RefTypeArret, Integer> {
 
  List<RefTypeArret> getAll();
  RefTypeArret getById(Integer id);
  RefTypeArret save(RefTypeArret refTypeArret);
  void delete(Integer id);

}