package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.Isochrone;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.Isochrone;
import fr.cerema.dsi.geofer.repositories.IsochroneRepository;
import fr.cerema.dsi.geofer.services.IsochroneService;
import java.util.List;
import java.util.Optional;
import fr.cerema.dsi.geofer.entities.Gare;
import fr.cerema.dsi.geofer.entities.RefTypeIsochrone;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class IsochroneServiceImpl extends GenericServiceImpl < Isochrone, Integer > implements IsochroneService {

    @Autowired
    private IsochroneRepository isochroneRepository;

    @Override
    public List < Isochrone > getAll() {
        return isochroneRepository.findAll();
    }

    @Override
    public Isochrone getById(Integer id) {
        return isochroneRepository.findById(id).orElse(null);
    }

    @Override
    public List < Isochrone > getByGareId(Integer gareId) {
        Gare gare = new Gare();
        gare.setId(gareId);
        return isochroneRepository.findByGare(gare);
    }

    @Override
    public List < Isochrone > getByRefTypeIsochroneId(Integer refTypeIsochroneId) {
        RefTypeIsochrone refTypeIsochrone = new RefTypeIsochrone();
        refTypeIsochrone.setId(refTypeIsochroneId);
        return isochroneRepository.findByRefTypeIsochrone(refTypeIsochrone);
    }

    @Override
    public Isochrone save(Isochrone isochrone) {
        return isochroneRepository.save(isochrone);
    }

    @Override
    public void delete(Integer id) {
        isochroneRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "getInfoByIsochrone", key = "{#gareIds}")
    public List < Object[] > getInfoByIsochrone(List < Integer > gareIds) {
        return isochroneRepository.getInfoByIsochrone(gareIds);
    }
    @Override
    @Cacheable(value = "getZoomIsochrone", key = "{#gareIds}")
    public List < Object[] > getZoomIsochrone(List < Integer > gareIds) {
        return isochroneRepository.getZoomIsochrone(gareIds);
    }
}