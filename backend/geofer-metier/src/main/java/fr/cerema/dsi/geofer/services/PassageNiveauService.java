package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.PassageNiveau;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface PassageNiveauService extends GenericService<PassageNiveau, Integer> {
 
  List<PassageNiveau> getAll();
  PassageNiveau getById(Integer id);
  List<PassageNiveau> getByRefClassePassageNiveauId(Integer refClassePassageNiveauId);
  PassageNiveau save(PassageNiveau passageNiveau);
  void delete(Integer id);

}