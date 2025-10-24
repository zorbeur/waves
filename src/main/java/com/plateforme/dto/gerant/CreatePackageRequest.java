package com.plateforme.dto.gerant;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CreatePackageRequest {
    private String nom;
    private String description;
    private BigDecimal prixPackage;
    private List<UUID> produitIds;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrixPackage() { return prixPackage; }
    public void setPrixPackage(BigDecimal prixPackage) { this.prixPackage = prixPackage; }
    public List<UUID> getProduitIds() { return produitIds; }
    public void setProduitIds(List<UUID> produitIds) { this.produitIds = produitIds; }
    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }
    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }
}
