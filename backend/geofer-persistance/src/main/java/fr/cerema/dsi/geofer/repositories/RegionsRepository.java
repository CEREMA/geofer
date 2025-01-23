package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.Regions;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface RegionsRepository extends GenericRepository < Regions, Integer > {

    /**
     * Lookup regions
     */
    @Query(value = "SELECT id,code,nom FROM regions ORDER BY nom", nativeQuery = true)
    List < Object[] > getLookUpRegions();

    /**
     * get regions by id
     */
    @Query(value = "SELECT id,code,nom,geom FROM regions WHERE id = :id", nativeQuery = true)
    List < Object[] > getRegionsByUID(@Param("id") Integer id);

}