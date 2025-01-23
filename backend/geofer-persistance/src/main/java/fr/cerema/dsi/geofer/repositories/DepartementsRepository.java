package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.Departements;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface DepartementsRepository extends GenericRepository < Departements, Integer > {

    /**
     * Lookup departements
     */
    @Query(value = "SELECT id,code,nom FROM departements ORDER BY nom", nativeQuery = true)
    List < Object[] > getLookUpDepartements();

    /**
     * Search departements by code
     */
    @Query(value = "SELECT code,nom,geom FROM departements WHERE code = :code", nativeQuery = true)
    List < Object[] > getSearchDepartements(@Param("code") String code);

}