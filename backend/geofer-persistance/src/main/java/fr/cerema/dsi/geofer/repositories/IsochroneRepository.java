package fr.cerema.dsi.geofer.repositories;

import fr.cerema.dsi.commons.repositories.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import fr.cerema.dsi.geofer.entities.Isochrone;
import java.util.List;
import fr.cerema.dsi.geofer.entities.Gare;
import fr.cerema.dsi.geofer.entities.RefTypeIsochrone;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface IsochroneRepository extends GenericRepository < Isochrone, Integer > {

    List < Isochrone > findByGare(Gare gare);
    List < Isochrone > findByRefTypeIsochrone(RefTypeIsochrone refTypeIsochrone);
    /**
     * Fetch informations about isochrones
     */
    @Query(value = "WITH LatestData AS ( SELECT di.ref_type_donnee_id, i.gare_id, MAX(di.annee) AS MaxYear FROM donnee_isochrone di JOIN isochrone i ON i.id = di.isochrone_id WHERE i.gare_id IN :gareIds GROUP BY di.ref_type_donnee_id, i.gare_id ) SELECT i.gare_id, di.nombre, di.annee, CASE WHEN di.ref_type_donnee_id = 1 THEN 'etablissementsDeSante' WHEN di.ref_type_donnee_id = 2 THEN 'etablissementsDeSport' WHEN di.ref_type_donnee_id = 3 THEN 'commerces' WHEN di.ref_type_donnee_id = 4 THEN 'chambresHotelEtEmplacementsCamping' WHEN di.ref_type_donnee_id = 5 THEN 'etablissementsDeLoisir' WHEN di.ref_type_donnee_id = 6 THEN 'salaries' WHEN di.ref_type_donnee_id = 7 THEN 'restaurants' WHEN di.ref_type_donnee_id = 8 THEN 'habitants' WHEN di.ref_type_donnee_id = 9 THEN 'eleves' ELSE 'inconnu' END AS type_donnee_camelcase, CASE WHEN i.ref_type_isochrone_id = 1 THEN CONCAT(CASE WHEN di.ref_type_donnee_id = 1 THEN 'sante15MinAPied' WHEN di.ref_type_donnee_id = 2 THEN 'sport15MinAPied' WHEN di.ref_type_donnee_id = 3 THEN 'commerces15MinAPied' WHEN di.ref_type_donnee_id = 4 THEN 'hotelsEtCampings15MinAPied' WHEN di.ref_type_donnee_id = 5 THEN 'loisirs15MinAPied' WHEN di.ref_type_donnee_id = 6 THEN 'salaries15MinAPied' WHEN di.ref_type_donnee_id = 7 THEN 'restaurants15MinAPied' WHEN di.ref_type_donnee_id = 8 THEN 'habitants15MinAPied' WHEN di.ref_type_donnee_id = 9 THEN 'eleves15MinAPied' ELSE 'inconnu15MinAPied' END, di.annee) WHEN i.ref_type_isochrone_id = 2 THEN CONCAT(CASE WHEN di.ref_type_donnee_id = 1 THEN 'sante30MinAPied' WHEN di.ref_type_donnee_id = 2 THEN 'sport30MinAPied' WHEN di.ref_type_donnee_id = 3 THEN 'commerces30MinAPied' WHEN di.ref_type_donnee_id = 4 THEN 'hotelsEtCampings30MinAPied' WHEN di.ref_type_donnee_id = 5 THEN 'loisirs30MinAPied' WHEN di.ref_type_donnee_id = 6 THEN 'salaries30MinAPied' WHEN di.ref_type_donnee_id = 7 THEN 'restaurants30MinAPied' WHEN di.ref_type_donnee_id = 8 THEN 'habitants30MinAPied' WHEN di.ref_type_donnee_id = 9 THEN 'eleves30MinAPied' ELSE 'inconnu30MinAPied' END, di.annee) WHEN i.ref_type_isochrone_id = 3 THEN CONCAT(CASE WHEN di.ref_type_donnee_id = 1 THEN 'sante10MinEnVoiture' WHEN di.ref_type_donnee_id = 2 THEN 'sport10MinEnVoiture' WHEN di.ref_type_donnee_id = 3 THEN 'commerces10MinEnVoiture' WHEN di.ref_type_donnee_id = 4 THEN 'hotelsEtCampings10MinEnVoiture' WHEN di.ref_type_donnee_id = 5 THEN 'loisirs10MinEnVoiture' WHEN di.ref_type_donnee_id = 6 THEN 'salaries10MinEnVoiture' WHEN di.ref_type_donnee_id = 7 THEN 'restaurants10MinEnVoiture' WHEN di.ref_type_donnee_id = 8 THEN 'habitants10MinEnVoiture' WHEN di.ref_type_donnee_id = 9 THEN 'eleves10MinEnVoiture' ELSE 'inconnu10MinEnVoiture' END, di.annee) ELSE 'inconnu' END AS nommage_camelcase FROM donnee_isochrone di JOIN isochrone i ON i.id = di.isochrone_id JOIN LatestData ld ON ld.ref_type_donnee_id = di.ref_type_donnee_id AND ld.gare_id = i.gare_id AND ld.MaxYear = di.annee", nativeQuery = true)
    List < Object[] > getInfoByIsochrone(@Param("gareIds") List < Integer > gareIds);

    /**
     * Fetch isochrones by gare_id
     */
    @Query(value = "SELECT id, ref_type_isochrone_id, gare_id, the_geom FROM isochrone WHERE isochrone.gare_id in :gareIds", nativeQuery = true)
    List < Object[] > getZoomIsochrone(@Param("gareIds") List < Integer > gareIds);

}