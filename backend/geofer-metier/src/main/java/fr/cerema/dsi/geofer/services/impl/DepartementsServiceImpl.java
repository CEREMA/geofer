package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.Departements;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.Departements;
import fr.cerema.dsi.geofer.repositories.DepartementsRepository;
import fr.cerema.dsi.geofer.services.DepartementsService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DepartementsServiceImpl extends GenericServiceImpl < Departements, Integer > implements DepartementsService {

    @Autowired
    private DepartementsRepository departementsRepository;

    @Override
    public List < Departements > getAll() {
        return departementsRepository.findAll();
    }

    @Override
    public Departements getById(Integer id) {
        return departementsRepository.findById(id).orElse(null);
    }

    @Override
    public Departements save(Departements departements) {
        return departementsRepository.save(departements);
    }

    @Override
    public void delete(Integer id) {
        departementsRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "getLookUpDepartements", key = "{}")
    public List < Object[] > getLookUpDepartements() {
        return departementsRepository.getLookUpDepartements();
    }
    @Override
    @Cacheable(value = "getSearchDepartements", key = "{#code}")
    public List < Object[] > getSearchDepartements(String code) {
        return departementsRepository.getSearchDepartements(code);
    }
}