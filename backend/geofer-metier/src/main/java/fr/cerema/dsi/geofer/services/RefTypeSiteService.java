package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.RefTypeSite;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface RefTypeSiteService extends GenericService<RefTypeSite, Integer> {
 
  List<RefTypeSite> getAll();
  RefTypeSite getById(Integer id);
  RefTypeSite save(RefTypeSite refTypeSite);
  void delete(Integer id);

}