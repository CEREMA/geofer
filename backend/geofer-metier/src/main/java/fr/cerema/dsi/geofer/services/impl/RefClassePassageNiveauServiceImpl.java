package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.RefClassePassageNiveau;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.RefClassePassageNiveau;
import fr.cerema.dsi.geofer.repositories.RefClassePassageNiveauRepository;
import fr.cerema.dsi.geofer.services.RefClassePassageNiveauService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RefClassePassageNiveauServiceImpl extends GenericServiceImpl<RefClassePassageNiveau, Integer> implements RefClassePassageNiveauService {

  @Autowired
  private RefClassePassageNiveauRepository refClassePassageNiveauRepository;

  @Override
  public List<RefClassePassageNiveau> getAll() {
    return refClassePassageNiveauRepository.findAll();
  }

  @Override
  public RefClassePassageNiveau getById(Integer id) {
    return refClassePassageNiveauRepository.findById(id).orElse(null);
  }

  @Override
  public RefClassePassageNiveau save(RefClassePassageNiveau refClassePassageNiveau) {
    return refClassePassageNiveauRepository.save(refClassePassageNiveau);
  }

  @Override
  public void delete(Integer id) {
    refClassePassageNiveauRepository.deleteById(id);
  }

}