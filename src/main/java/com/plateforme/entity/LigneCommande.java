package com.plateforme.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "lignes_commande")
public class LigneCommande extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    private Integer quantite;

    @Column(precision = 10, scale = 2)
    private BigDecimal prixUnitaire;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalLigne;

    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }
    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }
    public BigDecimal getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(BigDecimal prixUnitaire) { this.prixUnitaire = prixUnitaire; }
    public BigDecimal getTotalLigne() { return totalLigne; }
    public void setTotalLigne(BigDecimal totalLigne) { this.totalLigne = totalLigne; }
}
