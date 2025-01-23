package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.DonneeIsochrone;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface DonneeIsochroneService extends GenericService < DonneeIsochrone, Integer > {

    List < DonneeIsochrone > getAll();
    DonneeIsochrone getById(Integer id);
    List < DonneeIsochrone > getByIsochroneId(Integer isochroneId);
    List < DonneeIsochrone > getByRefTypeDonneeId(Integer refTypeDonneeId);
    DonneeIsochrone save(DonneeIsochrone donneeIsochrone);
    void delete(Integer id);

    public List < Object[] > getStats(List < Integer > gareIds);
}