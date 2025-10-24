package com.plateforme.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "gerants")
public class Gerant extends Utilisateur {

    private UUID boutiqueId;

    @ElementCollection
    private Set<String> permissions;

    public UUID getBoutiqueId() { return boutiqueId; }
    public void setBoutiqueId(UUID boutiqueId) { this.boutiqueId = boutiqueId; }
    public Set<String> getPermissions() { return permissions; }
    public void setPermissions(Set<String> permissions) { this.permissions = permissions; }
}
