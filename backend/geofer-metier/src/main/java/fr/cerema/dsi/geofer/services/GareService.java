package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.Gare;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface GareService extends GenericService < Gare, Integer > {

    List < Gare > getAll();
    Gare getById(Integer id);
    Gare save(Gare gare);
    void delete(Integer id);

    public List < Object[] > getListGare(List < Integer > gareIds);
    public List < Object[] > getStatsTable(List < Integer > gareIds);
}