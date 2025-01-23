package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.PassageNiveau;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.PassageNiveau;
import fr.cerema.dsi.geofer.repositories.PassageNiveauRepository;
import fr.cerema.dsi.geofer.services.PassageNiveauService;
import java.util.List;
import java.util.Optional;
import fr.cerema.dsi.geofer.entities.RefClassePassageNiveau;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PassageNiveauServiceImpl extends GenericServiceImpl<PassageNiveau, Integer> implements PassageNiveauService {

  @Autowired
  private PassageNiveauRepository passageNiveauRepository;

  @Override
  public List<PassageNiveau> getAll() {
    return passageNiveauRepository.findAll();
  }

  @Override
  public PassageNiveau getById(Integer id) {
    return passageNiveauRepository.findById(id).orElse(null);
  }

  @Override
  public List<PassageNiveau> getByRefClassePassageNiveauId(Integer refClassePassageNiveauId) {
    RefClassePassageNiveau refClassePassageNiveau = new RefClassePassageNiveau();
    refClassePassageNiveau.setId(refClassePassageNiveauId);
    return passageNiveauRepository.findByRefClassePassageNiveau(refClassePassageNiveau);
  }

  @Override
  public PassageNiveau save(PassageNiveau passageNiveau) {
    return passageNiveauRepository.save(passageNiveau);
  }

  @Override
  public void delete(Integer id) {
    passageNiveauRepository.deleteById(id);
  }

}