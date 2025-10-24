package com.plateforme.dto.gerant;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProduitRequest {
    private String nom;
    private String description;
    private BigDecimal prixOriginal;
    private BigDecimal prixActuel;
    private UUID categorieId;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrixOriginal() { return prixOriginal; }
    public void setPrixOriginal(BigDecimal prixOriginal) { this.prixOriginal = prixOriginal; }
    public BigDecimal getPrixActuel() { return prixActuel; }
    public void setPrixActuel(BigDecimal prixActuel) { this.prixActuel = prixActuel; }
    public UUID getCategorieId() { return categorieId; }
    public void setCategorieId(UUID categorieId) { this.categorieId = categorieId; }
}
