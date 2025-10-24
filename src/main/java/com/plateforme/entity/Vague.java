package com.plateforme.entity;

import com.plateforme.entity.enums.StatutVague;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vagues")
public class Vague extends BaseEntity {

    private String nom;
    private String description;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @Enumerated(EnumType.STRING)
    private StatutVague statut;

    @ManyToMany
    @JoinTable(
        name = "vague_produits",
        joinColumns = @JoinColumn(name = "vague_id"),
        inverseJoinColumns = @JoinColumn(name = "produit_id")
    )
    private List<Produit> produits;

    @ManyToMany
    private List<PackageProduit> packages;

    @OneToMany
    private List<Promotion> promotionsExclusives;

    @ManyToOne
    private Utilisateur createur;

    @CreationTimestamp
    private LocalDateTime dateCreation;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }
    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }
    public StatutVague getStatut() { return statut; }
    public void setStatut(StatutVague statut) { this.statut = statut; }
    public List<Produit> getProduits() { return produits; }
    public void setProduits(List<Produit> produits) { this.produits = produits; }
    public List<PackageProduit> getPackages() { return packages; }
    public void setPackages(List<PackageProduit> packages) { this.packages = packages; }
    public List<Promotion> getPromotionsExclusives() { return promotionsExclusives; }
    public void setPromotionsExclusives(List<Promotion> promotionsExclusives) { this.promotionsExclusives = promotionsExclusives; }
    public Utilisateur getCreateur() { return createur; }
    public void setCreateur(Utilisateur createur) { this.createur = createur; }
    public LocalDateTime getDateCreation() { return dateCreation; }
}
