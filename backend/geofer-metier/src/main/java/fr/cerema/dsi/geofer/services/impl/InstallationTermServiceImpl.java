package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.InstallationTerm;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.InstallationTerm;
import fr.cerema.dsi.geofer.repositories.InstallationTermRepository;
import fr.cerema.dsi.geofer.services.InstallationTermService;
import java.util.List;
import java.util.Optional;
import fr.cerema.dsi.geofer.entities.RefActivitePrincipale;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class InstallationTermServiceImpl extends GenericServiceImpl<InstallationTerm, Integer> implements InstallationTermService {

  @Autowired
  private InstallationTermRepository installationTermRepository;

  @Override
  public List<InstallationTerm> getAll() {
    return installationTermRepository.findAll();
  }

  @Override
  public InstallationTerm getById(Integer id) {
    return installationTermRepository.findById(id).orElse(null);
  }

  @Override
  public List<InstallationTerm> getByRefActivitePrincipaleId(Integer refActivitePrincipaleId) {
    RefActivitePrincipale refActivitePrincipale = new RefActivitePrincipale();
    refActivitePrincipale.setId(refActivitePrincipaleId);
    return installationTermRepository.findByRefActivitePrincipale(refActivitePrincipale);
  }

  @Override
  public InstallationTerm save(InstallationTerm installationTerm) {
    return installationTermRepository.save(installationTerm);
  }

  @Override
  public void delete(Integer id) {
    installationTermRepository.deleteById(id);
  }

}