package fr.cerema.dsi.commons.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ViewService<T> {
    /**
     * Récupère toutes les entités.
     *
     * @return Une liste de toutes les entités.
     */
    List<T> findAll();

    /**
     * Récupère une page d'entités.
     *
     * @param pageable Les informations de pagination.
     * @return Une page d'entités.
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Recherche une entité par son ID.
     *
     * @param id L'ID de l'entité à rechercher.
     * @return Un Optional contenant l'entité si elle existe, sinon un Optional vide.
     */
    Optional<T> findById(Long id);

    /**
     * Compte le nombre total d'entités.
     *
     * @return Le nombre total d'entités.
     */
    long count();

    /**
     * Vérifie si une entité existe avec l'ID donné.
     *
     * @param id L'ID à vérifier.
     * @return true si une entité existe avec cet ID, false sinon.
     */
    boolean existsById(Long id);
}