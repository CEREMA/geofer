package fr.cerema.dsi.geofer.entities;

import fr.cerema.dsi.commons.entities.GenericEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Constructeurs
    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

