package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.RefTypeIsochrone;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface RefTypeIsochroneService extends GenericService<RefTypeIsochrone, Integer> {
 
  List<RefTypeIsochrone> getAll();
  RefTypeIsochrone getById(Integer id);
  RefTypeIsochrone save(RefTypeIsochrone refTypeIsochrone);
  void delete(Integer id);

}