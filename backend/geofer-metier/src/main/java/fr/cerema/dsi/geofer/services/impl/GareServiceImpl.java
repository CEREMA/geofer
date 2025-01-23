package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.Gare;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.Gare;
import fr.cerema.dsi.geofer.repositories.GareRepository;
import fr.cerema.dsi.geofer.services.GareService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class GareServiceImpl extends GenericServiceImpl < Gare, Integer > implements GareService {

    @Autowired
    private GareRepository gareRepository;

    @Override
    public List < Gare > getAll() {
        return gareRepository.findAll();
    }

    @Override
    public Gare getById(Integer id) {
        return gareRepository.findById(id).orElse(null);
    }

    @Override
    public Gare save(Gare gare) {
        return gareRepository.save(gare);
    }

    @Override
    public void delete(Integer id) {
        gareRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "getListGare", key = "{#gareIds}")
    public List < Object[] > getListGare(List < Integer > gareIds) {
        return gareRepository.getListGare(gareIds);
    }
    @Override
    @Cacheable(value = "getStatsTable", key = "{#gareIds}")
    public List < Object[] > getStatsTable(List < Integer > gareIds) {
        return gareRepository.getStatsTable(gareIds);
    }
}