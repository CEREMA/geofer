package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.Communes;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.Communes;
import fr.cerema.dsi.geofer.repositories.CommunesRepository;
import fr.cerema.dsi.geofer.services.CommunesService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CommunesServiceImpl extends GenericServiceImpl < Communes, Integer > implements CommunesService {

    @Autowired
    private CommunesRepository communesRepository;

    @Override
    public List < Communes > getAll() {
        return communesRepository.findAll();
    }

    @Override
    public Communes getById(Integer id) {
        return communesRepository.findById(id).orElse(null);
    }

    @Override
    public Communes save(Communes communes) {
        return communesRepository.save(communes);
    }

    @Override
    public void delete(Integer id) {
        communesRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "getGaresByCommune", key = "{#insee_commune}")
    public List < Object[] > getGaresByCommune(String insee_commune) {
        return communesRepository.getGaresByCommune(insee_commune);
    }
    @Override
    @Cacheable(value = "getLookUpCommunes", key = "{}")
    public List < Object[] > getLookUpCommunes() {
        return communesRepository.getLookUpCommunes();
    }
    @Override
    @Cacheable(value = "getLookUpCommunesDetails", key = "{#insee_code}")
    public List < Object[] > getLookUpCommunesDetails(String insee_code) {
        return communesRepository.getLookUpCommunesDetails(insee_code);
    }
}