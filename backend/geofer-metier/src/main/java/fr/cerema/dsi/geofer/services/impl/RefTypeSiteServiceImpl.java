package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.RefTypeSite;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.RefTypeSite;
import fr.cerema.dsi.geofer.repositories.RefTypeSiteRepository;
import fr.cerema.dsi.geofer.services.RefTypeSiteService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RefTypeSiteServiceImpl extends GenericServiceImpl<RefTypeSite, Integer> implements RefTypeSiteService {

  @Autowired
  private RefTypeSiteRepository refTypeSiteRepository;

  @Override
  public List<RefTypeSite> getAll() {
    return refTypeSiteRepository.findAll();
  }

  @Override
  public RefTypeSite getById(Integer id) {
    return refTypeSiteRepository.findById(id).orElse(null);
  }

  @Override
  public RefTypeSite save(RefTypeSite refTypeSite) {
    return refTypeSiteRepository.save(refTypeSite);
  }

  @Override
  public void delete(Integer id) {
    refTypeSiteRepository.deleteById(id);
  }

}