package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.DonneeVoyageur;
import java.util.List;
import fr.cerema.dsi.geofer.entities.Gare;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface DonneeVoyageurRepository extends GenericRepository < DonneeVoyageur, Integer > {

    List < DonneeVoyageur > findByGare(Gare gare);
    /**
     * Fetch stats Voyageurs
     */
    @Query(value = "SELECT annee, SUM(nombre_voyageur) AS total_par_annee FROM donnee_voyageur WHERE gare_id in :gareIds GROUP BY annee ORDER BY annee", nativeQuery = true)
    List < Object[] > getAllVoyageurs(@Param("gareIds") List < Integer > gareIds);
    /**
     * Fetch all donnees voyageurs
     */
    @Query(value = "SELECT * FROM donnee_voyageur WHERE gare_id in :gareIds ORDER BY gare_id,annee", nativeQuery = true)
    List < Object[] > getDonneeVoyageurByGare(@Param("gareIds") List < Integer > gareIds);

    /**
     * Fetch frequentation donnees voyageur
     */
    @Query(value = "SELECT gare.id AS gare_id, gare.nom_gare, donnee_voyageur.annee, donnee_voyageur.nombre_voyageur, gare.si_ouverte FROM donnee_voyageur JOIN gare ON donnee_voyageur.gare_id = gare.id WHERE donnee_voyageur.annee >= (( SELECT max(donnee_voyageur_1.annee) AS max FROM donnee_voyageur donnee_voyageur_1)) and gare_id in :gareIds", nativeQuery = true)
    List < Object[] > getFrequentationVoyageur(@Param("gareIds") List < Integer > gareIds);

}