package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.InstallationTermRefMarchandise;
import java.util.List;
import fr.cerema.dsi.geofer.entities.InstallationTerm;
import fr.cerema.dsi.geofer.entities.RefMarchandise;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface InstallationTermRefMarchandiseRepository extends GenericRepository < InstallationTermRefMarchandise, Integer > {

    List < InstallationTermRefMarchandise > findByInstallationTerm(InstallationTerm installationTerm);
    List < InstallationTermRefMarchandise > findByRefMarchandise(RefMarchandise refMarchandise);

    /**
     * Search
     */
    @Query(value = "SELECT installation_term_ref_marchandise.id, installation_term_ref_marchandise.ref_marchandise_id, installation_term_ref_marchandise.si_par_train, installation_term_ref_marchandise.flux, ref_marchandise.code_nst, ref_marchandise.libelle FROM installation_term_ref_marchandise JOIN ref_marchandise ON ref_marchandise.id = installation_term_ref_marchandise.ref_marchandise_id WHERE installation_term_ref_marchandise.installation_term_id = :p_installation_term_id AND installation_term_ref_marchandise.si_par_train = true", nativeQuery = true)
    List < Object[] > getSearch(@Param("p_installation_term_id") Integer p_installation_term_id);

}