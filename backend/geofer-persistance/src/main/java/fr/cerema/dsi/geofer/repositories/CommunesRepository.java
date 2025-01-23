package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.Communes;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface CommunesRepository extends GenericRepository < Communes, Integer > {

    /**
     * Fetch gares by communes (insee)
     */
    @Query(value = "SELECT id,nom_commune,insee_commune,insee_departement,code_uic,nom_gare,si_ouverte,nom_aom,si_automates_tgv_intercites,si_automates_ter,si_poste_vente_guichet,si_libre_service_assiste,the_geom FROM gare WHERE insee_commune = :insee_commune ORDER BY id", nativeQuery = true)
    List < Object[] > getGaresByCommune(@Param("insee_commune") String insee_commune);
    /**
     * Lookup communes
     */
    @Query(value = "SELECT id,com_nom,com_code FROM communes ORDER BY com_nom", nativeQuery = true)
    List < Object[] > getLookUpCommunes();

    /**
     * Lookup communes details
     */
    @Query(value = "SELECT id,com_code insee,com_nom nom,geom FROM communes WHERE com_code = :insee_code", nativeQuery = true)
    List < Object[] > getLookUpCommunesDetails(@Param("insee_code") String insee_code);

}