package fr.cerema.dsi.geofer.services;

import fr.cerema.dsi.geofer.entities.DptRegions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fr.cerema.dsi.commons.services.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


public interface DptRegionsService extends GenericService < DptRegions, String > {

    List < DptRegions > getAll();
    DptRegions getById(String department_code);
    DptRegions save(DptRegions dptRegions);
    void delete(String department_code);

    public List < Object[] > getDeptByRegion(String code);
    public List < Object[] > getRegionByDept(String code);
}