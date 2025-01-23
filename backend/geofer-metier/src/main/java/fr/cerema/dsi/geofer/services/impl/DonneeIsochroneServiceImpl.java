package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.DonneeIsochrone;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.DonneeIsochrone;
import fr.cerema.dsi.geofer.repositories.DonneeIsochroneRepository;
import fr.cerema.dsi.geofer.services.DonneeIsochroneService;
import java.util.List;
import java.util.Optional;
import fr.cerema.dsi.geofer.entities.Isochrone;
import fr.cerema.dsi.geofer.entities.RefTypeDonnee;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DonneeIsochroneServiceImpl extends GenericServiceImpl < DonneeIsochrone, Integer > implements DonneeIsochroneService {

    @Autowired
    private DonneeIsochroneRepository donneeIsochroneRepository;

    @Override
    public List < DonneeIsochrone > getAll() {
        return donneeIsochroneRepository.findAll();
    }

    @Override
    public DonneeIsochrone getById(Integer id) {
        return donneeIsochroneRepository.findById(id).orElse(null);
    }

    @Override
    public List < DonneeIsochrone > getByIsochroneId(Integer isochroneId) {
        Isochrone isochrone = new Isochrone();
        isochrone.setId(isochroneId);
        return donneeIsochroneRepository.findByIsochrone(isochrone);
    }

    @Override
    public List < DonneeIsochrone > getByRefTypeDonneeId(Integer refTypeDonneeId) {
        RefTypeDonnee refTypeDonnee = new RefTypeDonnee();
        refTypeDonnee.setId(refTypeDonneeId);
        return donneeIsochroneRepository.findByRefTypeDonnee(refTypeDonnee);
    }

    @Override
    public DonneeIsochrone save(DonneeIsochrone donneeIsochrone) {
        return donneeIsochroneRepository.save(donneeIsochrone);
    }

    @Override
    public void delete(Integer id) {
        donneeIsochroneRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "getStats", key = "{#gareIds}")
    public List < Object[] > getStats(List < Integer > gareIds) {
        return donneeIsochroneRepository.getStats(gareIds);
    }
}