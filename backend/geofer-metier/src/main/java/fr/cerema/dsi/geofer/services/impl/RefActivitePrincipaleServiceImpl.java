package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.RefActivitePrincipale;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.RefActivitePrincipale;
import fr.cerema.dsi.geofer.repositories.RefActivitePrincipaleRepository;
import fr.cerema.dsi.geofer.services.RefActivitePrincipaleService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RefActivitePrincipaleServiceImpl extends GenericServiceImpl<RefActivitePrincipale, Integer> implements RefActivitePrincipaleService {

  @Autowired
  private RefActivitePrincipaleRepository refActivitePrincipaleRepository;

  @Override
  public List<RefActivitePrincipale> getAll() {
    return refActivitePrincipaleRepository.findAll();
  }

  @Override
  public RefActivitePrincipale getById(Integer id) {
    return refActivitePrincipaleRepository.findById(id).orElse(null);
  }

  @Override
  public RefActivitePrincipale save(RefActivitePrincipale refActivitePrincipale) {
    return refActivitePrincipaleRepository.save(refActivitePrincipale);
  }

  @Override
  public void delete(Integer id) {
    refActivitePrincipaleRepository.deleteById(id);
  }

}