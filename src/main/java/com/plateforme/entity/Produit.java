package com.plateforme.entity;

import com.plateforme.entity.enums.StatutProduit;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produits")
public class Produit extends BaseEntity {

    private String nom;
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal prixOriginal;

    @Column(precision = 10, scale = 2)
    private BigDecimal prixActuel;

    private Integer quantiteStock;
    private Integer seuilAlerte;

    @ElementCollection
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @Enumerated(EnumType.STRING)
    private StatutProduit statut;

    @ManyToOne
    private Utilisateur createur;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrixOriginal() { return prixOriginal; }
    public void setPrixOriginal(BigDecimal prixOriginal) { this.prixOriginal = prixOriginal; }
    public BigDecimal getPrixActuel() { return prixActuel; }
    public void setPrixActuel(BigDecimal prixActuel) { this.prixActuel = prixActuel; }
    public Integer getQuantiteStock() { return quantiteStock; }
    public void setQuantiteStock(Integer quantiteStock) { this.quantiteStock = quantiteStock; }
    public Integer getSeuilAlerte() { return seuilAlerte; }
    public void setSeuilAlerte(Integer seuilAlerte) { this.seuilAlerte = seuilAlerte; }
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }
    public StatutProduit getStatut() { return statut; }
    public void setStatut(StatutProduit statut) { this.statut = statut; }
    public Utilisateur getCreateur() { return createur; }
    public void setCreateur(Utilisateur createur) { this.createur = createur; }
}
