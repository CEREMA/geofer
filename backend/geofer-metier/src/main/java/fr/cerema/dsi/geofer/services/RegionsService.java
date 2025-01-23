package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.Regions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface RegionsService extends GenericService < Regions, Integer > {

    List < Regions > getAll();
    Regions getById(Integer id);
    Regions save(Regions regions);
    void delete(Integer id);

    public List < Object[] > getLookUpRegions();
    public List < Object[] > getRegionsByUID(Integer id);
}