package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.Ligne;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.Ligne;
import fr.cerema.dsi.geofer.repositories.LigneRepository;
import fr.cerema.dsi.geofer.services.LigneService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class LigneServiceImpl extends GenericServiceImpl<Ligne, Integer> implements LigneService {

  @Autowired
  private LigneRepository ligneRepository;

  @Override
  public List<Ligne> getAll() {
    return ligneRepository.findAll();
  }

  @Override
  public Ligne getById(Integer id) {
    return ligneRepository.findById(id).orElse(null);
  }

  @Override
  public Ligne save(Ligne ligne) {
    return ligneRepository.save(ligne);
  }

  @Override
  public void delete(Integer id) {
    ligneRepository.deleteById(id);
  }

}