package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.Regions;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.Regions;
import fr.cerema.dsi.geofer.repositories.RegionsRepository;
import fr.cerema.dsi.geofer.services.RegionsService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RegionsServiceImpl extends GenericServiceImpl < Regions, Integer > implements RegionsService {

    @Autowired
    private RegionsRepository regionsRepository;

    @Override
    public List < Regions > getAll() {
        return regionsRepository.findAll();
    }

    @Override
    public Regions getById(Integer id) {
        return regionsRepository.findById(id).orElse(null);
    }

    @Override
    public Regions save(Regions regions) {
        return regionsRepository.save(regions);
    }

    @Override
    public void delete(Integer id) {
        regionsRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "getLookUpRegions", key = "{}")
    public List < Object[] > getLookUpRegions() {
        return regionsRepository.getLookUpRegions();
    }
    @Override
    @Cacheable(value = "getRegionsByUID", key = "{#id}")
    public List < Object[] > getRegionsByUID(Integer id) {
        return regionsRepository.getRegionsByUID(id);
    }
}