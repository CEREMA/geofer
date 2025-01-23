package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.Departements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface DepartementsService extends GenericService < Departements, Integer > {

    List < Departements > getAll();
    Departements getById(Integer id);
    Departements save(Departements departements);
    void delete(Integer id);

    public List < Object[] > getLookUpDepartements();
    public List < Object[] > getSearchDepartements(String code);
}