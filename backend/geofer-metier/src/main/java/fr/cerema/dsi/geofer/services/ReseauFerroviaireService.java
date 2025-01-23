package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.ReseauFerroviaire;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface ReseauFerroviaireService extends GenericService<ReseauFerroviaire, Integer> {
 
  List<ReseauFerroviaire> getAll();
  ReseauFerroviaire getById(Integer id);
  ReseauFerroviaire save(ReseauFerroviaire reseauFerroviaire);
  void delete(Integer id);

}