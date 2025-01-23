package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.InstallationTermRefMarchandise;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface InstallationTermRefMarchandiseService extends GenericService < InstallationTermRefMarchandise, Integer > {

    List < InstallationTermRefMarchandise > getAll();
    InstallationTermRefMarchandise getById(Integer id);
    List < InstallationTermRefMarchandise > getByInstallationTermId(Integer installationTermId);
    List < InstallationTermRefMarchandise > getByRefMarchandiseId(Integer refMarchandiseId);
    InstallationTermRefMarchandise save(InstallationTermRefMarchandise installationTermRefMarchandise);
    void delete(Integer id);

    public List < Object[] > getSearch(Integer p_installation_term_id);
}