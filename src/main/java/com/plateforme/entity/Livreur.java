package com.plateforme.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "livreurs")
public class Livreur extends Utilisateur {
    private String zoneLivraison;
    private String vehicule;
    private boolean disponible = true;

    public String getZoneLivraison() { return zoneLivraison; }
    public void setZoneLivraison(String zoneLivraison) { this.zoneLivraison = zoneLivraison; }
    public String getVehicule() { return vehicule; }
    public void setVehicule(String vehicule) { this.vehicule = vehicule; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
