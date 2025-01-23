package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.DonneeArret;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface DonneeArretService extends GenericService < DonneeArret, Integer > {

    List < DonneeArret > getAll();
    DonneeArret getById(Integer id);
    List < DonneeArret > getByGareId(Integer gareId);
    List < DonneeArret > getByRefTypeArretId(Integer refTypeArretId);
    DonneeArret save(DonneeArret donneeArret);
    void delete(Integer id);

    public List < Object[] > getDonneesArret(List < Integer > gareIds);
}