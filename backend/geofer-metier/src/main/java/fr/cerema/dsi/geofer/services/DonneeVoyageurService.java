package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.DonneeVoyageur;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface DonneeVoyageurService extends GenericService < DonneeVoyageur, Integer > {

    List < DonneeVoyageur > getAll();
    DonneeVoyageur getById(Integer id);
    List < DonneeVoyageur > getByGareId(Integer gareId);
    DonneeVoyageur save(DonneeVoyageur donneeVoyageur);
    void delete(Integer id);

    public List < Object[] > getAllVoyageurs(List < Integer > gareIds);
    public List < Object[] > getDonneeVoyageurByGare(List < Integer > gareIds);
    public List < Object[] > getFrequentationVoyageur(List < Integer > gareIds);
}