package com.plateforme.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "packages")
public class PackageProduit extends BaseEntity {
    private String nom;
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal prixOriginalTotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal prixPackage;

    @ManyToMany
    @JoinTable(
        name = "package_produits",
        joinColumns = @JoinColumn(name = "package_id"),
        inverseJoinColumns = @JoinColumn(name = "produit_id")
    )
    private List<Produit> produits;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private boolean actif = true;

    @ManyToOne
    private Utilisateur createur;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrixOriginalTotal() { return prixOriginalTotal; }
    public void setPrixOriginalTotal(BigDecimal prixOriginalTotal) { this.prixOriginalTotal = prixOriginalTotal; }
    public BigDecimal getPrixPackage() { return prixPackage; }
    public void setPrixPackage(BigDecimal prixPackage) { this.prixPackage = prixPackage; }
    public List<Produit> getProduits() { return produits; }
    public void setProduits(List<Produit> produits) { this.produits = produits; }
    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }
    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }
    public boolean isActif() { return actif; }
    public void setActif(boolean actif) { this.actif = actif; }
    public Utilisateur getCreateur() { return createur; }
    public void setCreateur(Utilisateur createur) { this.createur = createur; }
}
