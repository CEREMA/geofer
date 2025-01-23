package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.Communes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface CommunesService extends GenericService < Communes, Integer > {

    List < Communes > getAll();
    Communes getById(Integer id);
    Communes save(Communes communes);
    void delete(Integer id);

    public List < Object[] > getGaresByCommune(String insee_commune);
    public List < Object[] > getLookUpCommunes();
    public List < Object[] > getLookUpCommunesDetails(String insee_code);
}