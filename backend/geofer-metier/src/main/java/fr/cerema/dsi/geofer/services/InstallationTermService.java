package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.InstallationTerm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface InstallationTermService extends GenericService<InstallationTerm, Integer> {
 
  List<InstallationTerm> getAll();
  InstallationTerm getById(Integer id);
  List<InstallationTerm> getByRefActivitePrincipaleId(Integer refActivitePrincipaleId);
  InstallationTerm save(InstallationTerm installationTerm);
  void delete(Integer id);

}