package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.DonneeArret;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.DonneeArret;
import fr.cerema.dsi.geofer.repositories.DonneeArretRepository;
import fr.cerema.dsi.geofer.services.DonneeArretService;
import java.util.List;
import java.util.Optional;
import fr.cerema.dsi.geofer.entities.Gare;
import fr.cerema.dsi.geofer.entities.RefTypeArret;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DonneeArretServiceImpl extends GenericServiceImpl < DonneeArret, Integer > implements DonneeArretService {

    @Autowired
    private DonneeArretRepository donneeArretRepository;

    @Override
    public List < DonneeArret > getAll() {
        return donneeArretRepository.findAll();
    }

    @Override
    public DonneeArret getById(Integer id) {
        return donneeArretRepository.findById(id).orElse(null);
    }

    @Override
    public List < DonneeArret > getByGareId(Integer gareId) {
        Gare gare = new Gare();
        gare.setId(gareId);
        return donneeArretRepository.findByGare(gare);
    }

    @Override
    public List < DonneeArret > getByRefTypeArretId(Integer refTypeArretId) {
        RefTypeArret refTypeArret = new RefTypeArret();
        refTypeArret.setId(refTypeArretId);
        return donneeArretRepository.findByRefTypeArret(refTypeArret);
    }

    @Override
    public DonneeArret save(DonneeArret donneeArret) {
        return donneeArretRepository.save(donneeArret);
    }

    @Override
    public void delete(Integer id) {
        donneeArretRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "getDonneesArret", key = "{#gareIds}")
    public List < Object[] > getDonneesArret(List < Integer > gareIds) {
        return donneeArretRepository.getDonneesArret(gareIds);
    }
}