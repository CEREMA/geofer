package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.RefTypeIsochrone;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.RefTypeIsochrone;
import fr.cerema.dsi.geofer.repositories.RefTypeIsochroneRepository;
import fr.cerema.dsi.geofer.services.RefTypeIsochroneService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RefTypeIsochroneServiceImpl extends GenericServiceImpl<RefTypeIsochrone, Integer> implements RefTypeIsochroneService {

  @Autowired
  private RefTypeIsochroneRepository refTypeIsochroneRepository;

  @Override
  public List<RefTypeIsochrone> getAll() {
    return refTypeIsochroneRepository.findAll();
  }

  @Override
  public RefTypeIsochrone getById(Integer id) {
    return refTypeIsochroneRepository.findById(id).orElse(null);
  }

  @Override
  public RefTypeIsochrone save(RefTypeIsochrone refTypeIsochrone) {
    return refTypeIsochroneRepository.save(refTypeIsochrone);
  }

  @Override
  public void delete(Integer id) {
    refTypeIsochroneRepository.deleteById(id);
  }

}