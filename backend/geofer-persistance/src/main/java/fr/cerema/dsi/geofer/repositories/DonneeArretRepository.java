package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.DonneeArret;
import java.util.List;
import fr.cerema.dsi.geofer.entities.Gare;
import fr.cerema.dsi.geofer.entities.RefTypeArret;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface DonneeArretRepository extends GenericRepository < DonneeArret, Integer > {

    List < DonneeArret > findByGare(Gare gare);
    List < DonneeArret > findByRefTypeArret(RefTypeArret refTypeArret);

    /**
     * Fetch gares by communes (insee)
     */
    @Query(value = "SELECT donnee_arret.annee, ref_type_arret.id AS ref_type_arret, ref_type_arret.libelle, donnee_arret.nombre_arret, donnee_arret.gare_id, donnee_arret.id, gare.nom_gare FROM donnee_arret JOIN ref_type_arret ON ref_type_arret.id = donnee_arret.ref_type_arret_id JOIN gare ON gare.id = donnee_arret.gare_id WHERE gare.id in :gareIds ORDER BY donnee_arret.annee, ref_type_arret.libelle, donnee_arret.nombre_arret DESC", nativeQuery = true)
    List < Object[] > getDonneesArret(@Param("gareIds") List < Integer > gareIds);

}