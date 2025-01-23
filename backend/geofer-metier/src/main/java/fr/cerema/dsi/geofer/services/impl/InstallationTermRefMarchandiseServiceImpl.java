package fr.cerema.dsi.geofer.services.impl;

import fr.cerema.dsi.geofer.entities.InstallationTermRefMarchandise;
import fr.cerema.dsi.commons.services.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import fr.cerema.dsi.geofer.entities.InstallationTermRefMarchandise;
import fr.cerema.dsi.geofer.repositories.InstallationTermRefMarchandiseRepository;
import fr.cerema.dsi.geofer.services.InstallationTermRefMarchandiseService;
import java.util.List;
import java.util.Optional;
import fr.cerema.dsi.geofer.entities.InstallationTerm;
import fr.cerema.dsi.geofer.entities.RefMarchandise;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class InstallationTermRefMarchandiseServiceImpl extends GenericServiceImpl < InstallationTermRefMarchandise, Integer > implements InstallationTermRefMarchandiseService {

    @Autowired
    private InstallationTermRefMarchandiseRepository installationTermRefMarchandiseRepository;

    @Override
    public List < InstallationTermRefMarchandise > getAll() {
        return installationTermRefMarchandiseRepository.findAll();
    }

    @Override
    public InstallationTermRefMarchandise getById(Integer id) {
        return installationTermRefMarchandiseRepository.findById(id).orElse(null);
    }

    @Override
    public List < InstallationTermRefMarchandise > getByInstallationTermId(Integer installationTermId) {
        InstallationTerm installationTerm = new InstallationTerm();
        installationTerm.setId(installationTermId);
        return installationTermRefMarchandiseRepository.findByInstallationTerm(installationTerm);
    }

    @Override
    public List < InstallationTermRefMarchandise > getByRefMarchandiseId(Integer refMarchandiseId) {
        RefMarchandise refMarchandise = new RefMarchandise();
        refMarchandise.setId(refMarchandiseId);
        return installationTermRefMarchandiseRepository.findByRefMarchandise(refMarchandise);
    }

    @Override
    public InstallationTermRefMarchandise save(InstallationTermRefMarchandise installationTermRefMarchandise) {
        return installationTermRefMarchandiseRepository.save(installationTermRefMarchandise);
    }

    @Override
    public void delete(Integer id) {
        installationTermRefMarchandiseRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "getSearch", key = "{#p_installation_term_id}")
    public List < Object[] > getSearch(Integer p_installation_term_id) {
        return installationTermRefMarchandiseRepository.getSearch(p_installation_term_id);
    }
}