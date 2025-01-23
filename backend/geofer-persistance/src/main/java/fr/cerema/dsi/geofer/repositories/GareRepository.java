package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.Gare;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface GareRepository extends GenericRepository < Gare, Integer > {

    /**
     * List gares
     */
    @Query(value = "SELECT gare.code_uic, gare.id AS gare_id, gare.insee_commune, gare.insee_departement, gare.nom_aom, gare.nom_commune, gare.nom_gare, gare.si_automates_ter, gare.si_automates_tgv_intercites, gare.si_libre_service_assiste, gare.si_ouverte, gare.si_poste_vente_guichet, donnee_arret.annee, donnee_arret.nombre_arret, donnee_arret.ref_type_arret_id FROM gare LEFT JOIN donnee_arret ON donnee_arret.gare_id = gare.id WHERE gare_id in :gareIds", nativeQuery = true)
    List < Object[] > getListGare(@Param("gareIds") List < Integer > gareIds);

    /**
     * Search
     */
    @Query(value = "SELECT g.nom_gare, CASE WHEN g.si_ouverte = true THEN 'ouverte' WHEN g.si_ouverte = false THEN 'fermée' ELSE 'statut inconnu' END AS statut_gare, CAST(sum(da.nombre_arret) AS INTEGER) AS nombre_arrets_total, g.nom_aom, dv.annee anneeVoyageurs, da.annee anneeArrets, CAST(dv.nombre_voyageur AS INTEGER) AS voyageurs, g.id AS gare_id FROM gare g JOIN donnee_arret da ON da.gare_id = g.id JOIN ( SELECT DISTINCT ON (donnee_voyageur.gare_id) donnee_voyageur.id, donnee_voyageur.annee, donnee_voyageur.nombre_voyageur, donnee_voyageur.gare_id FROM donnee_voyageur ORDER BY donnee_voyageur.gare_id, donnee_voyageur.annee DESC ) dv ON dv.gare_id = g.id WHERE g.id IN :gareIds GROUP BY g.nom_gare, g.nom_aom, dv.id, dv.annee, da.annee, dv.nombre_voyageur, g.id", nativeQuery = true)
    List < Object[] > getStatsTable(@Param("gareIds") List < Integer > gareIds);

}