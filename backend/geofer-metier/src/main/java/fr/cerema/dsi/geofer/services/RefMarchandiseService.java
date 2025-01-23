package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.RefMarchandise;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface RefMarchandiseService extends GenericService<RefMarchandise, Integer> {
 
  List<RefMarchandise> getAll();
  RefMarchandise getById(Integer id);
  RefMarchandise save(RefMarchandise refMarchandise);
  void delete(Integer id);

}