package com.plateforme.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Categorie extends BaseEntity {

    private String nom;
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Categorie parent;

    @ManyToOne
    @JoinColumn(name = "createur_id")
    private Utilisateur createur;

    private boolean actif = true;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Categorie getParent() { return parent; }
    public void setParent(Categorie parent) { this.parent = parent; }
    public Utilisateur getCreateur() { return createur; }
    public void setCreateur(Utilisateur createur) { this.createur = createur; }
    public boolean isActif() { return actif; }
    public void setActif(boolean actif) { this.actif = actif; }
}
