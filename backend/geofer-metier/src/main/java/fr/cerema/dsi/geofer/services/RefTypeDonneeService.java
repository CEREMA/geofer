package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.RefTypeDonnee;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface RefTypeDonneeService extends GenericService<RefTypeDonnee, Integer> {
 
  List<RefTypeDonnee> getAll();
  RefTypeDonnee getById(Integer id);
  RefTypeDonnee save(RefTypeDonnee refTypeDonnee);
  void delete(Integer id);

}