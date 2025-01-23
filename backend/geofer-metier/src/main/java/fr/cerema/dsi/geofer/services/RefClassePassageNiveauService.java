package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.RefClassePassageNiveau;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface RefClassePassageNiveauService extends GenericService<RefClassePassageNiveau, Integer> {
 
  List<RefClassePassageNiveau> getAll();
  RefClassePassageNiveau getById(Integer id);
  RefClassePassageNiveau save(RefClassePassageNiveau refClassePassageNiveau);
  void delete(Integer id);

}