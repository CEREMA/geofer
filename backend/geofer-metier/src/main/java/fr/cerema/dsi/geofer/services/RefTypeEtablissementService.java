package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.RefTypeEtablissement;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface RefTypeEtablissementService extends GenericService<RefTypeEtablissement, Integer> {
 
  List<RefTypeEtablissement> getAll();
  RefTypeEtablissement getById(Integer id);
  RefTypeEtablissement save(RefTypeEtablissement refTypeEtablissement);
  void delete(Integer id);

}