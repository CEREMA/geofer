package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.Isochrone;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface IsochroneService extends GenericService < Isochrone, Integer > {

    List < Isochrone > getAll();
    Isochrone getById(Integer id);
    List < Isochrone > getByGareId(Integer gareId);
    List < Isochrone > getByRefTypeIsochroneId(Integer refTypeIsochroneId);
    Isochrone save(Isochrone isochrone);
    void delete(Integer id);

    public List < Object[] > getInfoByIsochrone(List < Integer > gareIds);
    public List < Object[] > getZoomIsochrone(List < Integer > gareIds);
}