package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.DonneeIsochrone;
import java.util.List;
import fr.cerema.dsi.geofer.entities.Isochrone;
import fr.cerema.dsi.geofer.entities.RefTypeDonnee;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface DonneeIsochroneRepository extends GenericRepository < DonneeIsochrone, Integer > {

    List < DonneeIsochrone > findByIsochrone(Isochrone isochrone);
    List < DonneeIsochrone > findByRefTypeDonnee(RefTypeDonnee refTypeDonnee);

    /**
     * Fetch gares stats
     */
    @Query(value = "SELECT rtd.type_donnee, di.annee, di.nombre, i.gare_id, rtd.groupe_donnee, rti.libelle libelle_type_isochrone FROM donnee_isochrone di JOIN isochrone i ON i.id = di.isochrone_id JOIN ref_type_isochrone rti ON i.ref_type_isochrone_id = rti.id JOIN ref_type_donnee rtd ON di.ref_type_donnee_id = rtd.id WHERE i.gare_id IN :gareIds", nativeQuery = true)
    List < Object[] > getStats(@Param("gareIds") List < Integer > gareIds);

}