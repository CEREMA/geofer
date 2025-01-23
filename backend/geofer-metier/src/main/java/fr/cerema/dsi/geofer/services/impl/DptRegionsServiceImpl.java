package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.DptRegions;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.DptRegions;
import fr.cerema.dsi.geofer.repositories.DptRegionsRepository;
import fr.cerema.dsi.geofer.services.DptRegionsService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DptRegionsServiceImpl extends GenericServiceImpl < DptRegions, String > implements DptRegionsService {

    @Autowired
    private DptRegionsRepository dptRegionsRepository;

    @Override
    public List < DptRegions > getAll() {
        return dptRegionsRepository.findAll();
    }

    @Override
    public DptRegions getById(String department_code) {
        return dptRegionsRepository.findById(department_code).orElse(null);
    }

    @Override
    public DptRegions save(DptRegions dptRegions) {
        return dptRegionsRepository.save(dptRegions);
    }

    @Override
    public void delete(String department_code) {
        dptRegionsRepository.deleteById(department_code);
    }

    @Override
    @Cacheable(value = "getDeptByRegion", key = "{#code}")
    public List < Object[] > getDeptByRegion(String code) {
        return dptRegionsRepository.getDeptByRegion(code);
    }
    @Override
    @Cacheable(value = "getRegionByDept", key = "{#code}")
    public List < Object[] > getRegionByDept(String code) {
        return dptRegionsRepository.getRegionByDept(code);
    }
}