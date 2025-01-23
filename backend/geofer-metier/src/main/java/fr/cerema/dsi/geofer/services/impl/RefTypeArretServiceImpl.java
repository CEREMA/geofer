package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.RefTypeArret;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.RefTypeArret;
import fr.cerema.dsi.geofer.repositories.RefTypeArretRepository;
import fr.cerema.dsi.geofer.services.RefTypeArretService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RefTypeArretServiceImpl extends GenericServiceImpl<RefTypeArret, Integer> implements RefTypeArretService {

  @Autowired
  private RefTypeArretRepository refTypeArretRepository;

  @Override
  public List<RefTypeArret> getAll() {
    return refTypeArretRepository.findAll();
  }

  @Override
  public RefTypeArret getById(Integer id) {
    return refTypeArretRepository.findById(id).orElse(null);
  }

  @Override
  public RefTypeArret save(RefTypeArret refTypeArret) {
    return refTypeArretRepository.save(refTypeArret);
  }

  @Override
  public void delete(Integer id) {
    refTypeArretRepository.deleteById(id);
  }

}