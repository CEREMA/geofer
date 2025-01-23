package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.RefTypeEtablissement;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.RefTypeEtablissement;
import fr.cerema.dsi.geofer.repositories.RefTypeEtablissementRepository;
import fr.cerema.dsi.geofer.services.RefTypeEtablissementService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RefTypeEtablissementServiceImpl extends GenericServiceImpl<RefTypeEtablissement, Integer> implements RefTypeEtablissementService {

  @Autowired
  private RefTypeEtablissementRepository refTypeEtablissementRepository;

  @Override
  public List<RefTypeEtablissement> getAll() {
    return refTypeEtablissementRepository.findAll();
  }

  @Override
  public RefTypeEtablissement getById(Integer id) {
    return refTypeEtablissementRepository.findById(id).orElse(null);
  }

  @Override
  public RefTypeEtablissement save(RefTypeEtablissement refTypeEtablissement) {
    return refTypeEtablissementRepository.save(refTypeEtablissement);
  }

  @Override
  public void delete(Integer id) {
    refTypeEtablissementRepository.deleteById(id);
  }

}