package com.plateforme.dto.gerant;

import com.plateforme.entity.enums.TypePromotion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreatePromotionRequest {
    private UUID produitId;
    private TypePromotion type;
    private BigDecimal valeur;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public UUID getProduitId() { return produitId; }
    public void setProduitId(UUID produitId) { this.produitId = produitId; }
    public TypePromotion getType() { return type; }
    public void setType(TypePromotion type) { this.type = type; }
    public BigDecimal getValeur() { return valeur; }
    public void setValeur(BigDecimal valeur) { this.valeur = valeur; }
    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }
    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }
}
