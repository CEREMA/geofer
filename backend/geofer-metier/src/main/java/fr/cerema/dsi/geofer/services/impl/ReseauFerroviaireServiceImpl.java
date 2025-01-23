package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.ReseauFerroviaire;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.ReseauFerroviaire;
import fr.cerema.dsi.geofer.repositories.ReseauFerroviaireRepository;
import fr.cerema.dsi.geofer.services.ReseauFerroviaireService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ReseauFerroviaireServiceImpl extends GenericServiceImpl<ReseauFerroviaire, Integer> implements ReseauFerroviaireService {

  @Autowired
  private ReseauFerroviaireRepository reseauFerroviaireRepository;

  @Override
  public List<ReseauFerroviaire> getAll() {
    return reseauFerroviaireRepository.findAll();
  }

  @Override
  public ReseauFerroviaire getById(Integer id) {
    return reseauFerroviaireRepository.findById(id).orElse(null);
  }

  @Override
  public ReseauFerroviaire save(ReseauFerroviaire reseauFerroviaire) {
    return reseauFerroviaireRepository.save(reseauFerroviaire);
  }

  @Override
  public void delete(Integer id) {
    reseauFerroviaireRepository.deleteById(id);
  }

}