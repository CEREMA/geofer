package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.RefTypeDonnee;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.RefTypeDonnee;
import fr.cerema.dsi.geofer.repositories.RefTypeDonneeRepository;
import fr.cerema.dsi.geofer.services.RefTypeDonneeService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RefTypeDonneeServiceImpl extends GenericServiceImpl<RefTypeDonnee, Integer> implements RefTypeDonneeService {

  @Autowired
  private RefTypeDonneeRepository refTypeDonneeRepository;

  @Override
  public List<RefTypeDonnee> getAll() {
    return refTypeDonneeRepository.findAll();
  }

  @Override
  public RefTypeDonnee getById(Integer id) {
    return refTypeDonneeRepository.findById(id).orElse(null);
  }

  @Override
  public RefTypeDonnee save(RefTypeDonnee refTypeDonnee) {
    return refTypeDonneeRepository.save(refTypeDonnee);
  }

  @Override
  public void delete(Integer id) {
    refTypeDonneeRepository.deleteById(id);
  }

}