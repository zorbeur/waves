package com.plateforme.entity;

import com.plateforme.entity.enums.TypePromotion;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "promotions")
public class Promotion extends BaseEntity {

    @ManyToOne
    private Produit produit;

    @Enumerated(EnumType.STRING)
    private TypePromotion type;

    private BigDecimal valeur;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private boolean active = true;

    @ManyToOne
    private Utilisateur createur;

    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
    public TypePromotion getType() { return type; }
    public void setType(TypePromotion type) { this.type = type; }
    public BigDecimal getValeur() { return valeur; }
    public void setValeur(BigDecimal valeur) { this.valeur = valeur; }
    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }
    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public Utilisateur getCreateur() { return createur; }
    public void setCreateur(Utilisateur createur) { this.createur = createur; }
}
