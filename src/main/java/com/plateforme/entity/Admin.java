package com.plateforme.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends Utilisateur {
    private String niveauAcces;

    public String getNiveauAcces() { return niveauAcces; }
    public void setNiveauAcces(String niveauAcces) { this.niveauAcces = niveauAcces; }
}
