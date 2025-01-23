package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.DonneeVoyageur;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.DonneeVoyageur;
import fr.cerema.dsi.geofer.repositories.DonneeVoyageurRepository;
import fr.cerema.dsi.geofer.services.DonneeVoyageurService;
import java.util.List;
import java.util.Optional;
import fr.cerema.dsi.geofer.entities.Gare;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DonneeVoyageurServiceImpl extends GenericServiceImpl < DonneeVoyageur, Integer > implements DonneeVoyageurService {

    @Autowired
    private DonneeVoyageurRepository donneeVoyageurRepository;

    @Override
    public List < DonneeVoyageur > getAll() {
        return donneeVoyageurRepository.findAll();
    }

    @Override
    public DonneeVoyageur getById(Integer id) {
        return donneeVoyageurRepository.findById(id).orElse(null);
    }

    @Override
    public List < DonneeVoyageur > getByGareId(Integer gareId) {
        Gare gare = new Gare();
        gare.setId(gareId);
        return donneeVoyageurRepository.findByGare(gare);
    }

    @Override
    public DonneeVoyageur save(DonneeVoyageur donneeVoyageur) {
        return donneeVoyageurRepository.save(donneeVoyageur);
    }

    @Override
    public void delete(Integer id) {
        donneeVoyageurRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "getAllVoyageurs", key = "{#gareIds}")
    public List < Object[] > getAllVoyageurs(List < Integer > gareIds) {
        return donneeVoyageurRepository.getAllVoyageurs(gareIds);
    }
    @Override
    @Cacheable(value = "getDonneeVoyageurByGare", key = "{#gareIds}")
    public List < Object[] > getDonneeVoyageurByGare(List < Integer > gareIds) {
        return donneeVoyageurRepository.getDonneeVoyageurByGare(gareIds);
    }
    @Override
    @Cacheable(value = "getFrequentationVoyageur", key = "{#gareIds}")
    public List < Object[] > getFrequentationVoyageur(List < Integer > gareIds) {
        return donneeVoyageurRepository.getFrequentationVoyageur(gareIds);
    }
}