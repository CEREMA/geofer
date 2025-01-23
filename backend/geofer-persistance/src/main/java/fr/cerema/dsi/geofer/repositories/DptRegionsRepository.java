package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.DptRegions;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface DptRegionsRepository extends GenericRepository < DptRegions, String > {

    /**
     * Search Departement
     */
    @Query(value = "SELECT department_code,department_name,region_code,region_name FROM dpt_regions WHERE region_code = :code", nativeQuery = true)
    List < Object[] > getDeptByRegion(@Param("code") String code);

    /**
     * Search Region
     */
    @Query(value = "SELECT department_code,department_name,region_code,region_name FROM dpt_regions WHERE department_code = :code", nativeQuery = true)
    List < Object[] > getRegionByDept(@Param("code") String code);

}