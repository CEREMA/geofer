package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.EtablissementScolaire;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.EtablissementScolaire;
import fr.cerema.dsi.geofer.repositories.EtablissementScolaireRepository;
import fr.cerema.dsi.geofer.services.EtablissementScolaireService;
import java.util.List;
import java.util.Optional;
import fr.cerema.dsi.geofer.entities.RefTypeEtablissement;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class EtablissementScolaireServiceImpl extends GenericServiceImpl<EtablissementScolaire, Integer> implements EtablissementScolaireService {

  @Autowired
  private EtablissementScolaireRepository etablissementScolaireRepository;

  @Override
  public List<EtablissementScolaire> getAll() {
    return etablissementScolaireRepository.findAll();
  }

  @Override
  public EtablissementScolaire getById(Integer id) {
    return etablissementScolaireRepository.findById(id).orElse(null);
  }

  @Override
  public List<EtablissementScolaire> getByRefTypeEtablissementId(Integer refTypeEtablissementId) {
    RefTypeEtablissement refTypeEtablissement = new RefTypeEtablissement();
    refTypeEtablissement.setId(refTypeEtablissementId);
    return etablissementScolaireRepository.findByRefTypeEtablissement(refTypeEtablissement);
  }

  @Override
  public EtablissementScolaire save(EtablissementScolaire etablissementScolaire) {
    return etablissementScolaireRepository.save(etablissementScolaire);
  }

  @Override
  public void delete(Integer id) {
    etablissementScolaireRepository.deleteById(id);
  }

}