package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.RefMarchandise;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.RefMarchandise;
import fr.cerema.dsi.geofer.repositories.RefMarchandiseRepository;
import fr.cerema.dsi.geofer.services.RefMarchandiseService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RefMarchandiseServiceImpl extends GenericServiceImpl<RefMarchandise, Integer> implements RefMarchandiseService {

  @Autowired
  private RefMarchandiseRepository refMarchandiseRepository;

  @Override
  public List<RefMarchandise> getAll() {
    return refMarchandiseRepository.findAll();
  }

  @Override
  public RefMarchandise getById(Integer id) {
    return refMarchandiseRepository.findById(id).orElse(null);
  }

  @Override
  public RefMarchandise save(RefMarchandise refMarchandise) {
    return refMarchandiseRepository.save(refMarchandise);
  }

  @Override
  public void delete(Integer id) {
    refMarchandiseRepository.deleteById(id);
  }

}