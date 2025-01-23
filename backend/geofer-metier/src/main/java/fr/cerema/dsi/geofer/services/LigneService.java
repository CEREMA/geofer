package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.Ligne;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface LigneService extends GenericService<Ligne, Integer> {
 
  List<Ligne> getAll();
  Ligne getById(Integer id);
  Ligne save(Ligne ligne);
  void delete(Integer id);

}