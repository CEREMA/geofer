package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.EtablissementScolaire;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface EtablissementScolaireService extends GenericService<EtablissementScolaire, Integer> {
 
  List<EtablissementScolaire> getAll();
  EtablissementScolaire getById(Integer id);
  List<EtablissementScolaire> getByRefTypeEtablissementId(Integer refTypeEtablissementId);
  EtablissementScolaire save(EtablissementScolaire etablissementScolaire);
  void delete(Integer id);

}