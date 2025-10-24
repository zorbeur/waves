package com.plateforme.entity;

import com.plateforme.entity.enums.StatutLivraison;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "livraisons")
public class Livraison extends BaseEntity {

    @OneToOne
    private Commande commande;

    @ManyToOne
    private Livreur livreur;

    private LocalDateTime datePlanification;
    private LocalDateTime dateLivraisonEstimee;
    private LocalDateTime dateLivraisonReelle;

    @Enumerated(EnumType.STRING)
    private StatutLivraison statut;

    private String notesLivraison;

    @Lob
    private String signatureClient;

    private boolean confirmationQRCode = false;
    private String tokenConfirmation;

    @Lob
    private String preuveScanQR; // JSON

    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }
    public Livreur getLivreur() { return livreur; }
    public void setLivreur(Livreur livreur) { this.livreur = livreur; }
    public LocalDateTime getDatePlanification() { return datePlanification; }
    public void setDatePlanification(LocalDateTime datePlanification) { this.datePlanification = datePlanification; }
    public LocalDateTime getDateLivraisonEstimee() { return dateLivraisonEstimee; }
    public void setDateLivraisonEstimee(LocalDateTime dateLivraisonEstimee) { this.dateLivraisonEstimee = dateLivraisonEstimee; }
    public LocalDateTime getDateLivraisonReelle() { return dateLivraisonReelle; }
    public void setDateLivraisonReelle(LocalDateTime dateLivraisonReelle) { this.dateLivraisonReelle = dateLivraisonReelle; }
    public StatutLivraison getStatut() { return statut; }
    public void setStatut(StatutLivraison statut) { this.statut = statut; }
    public String getNotesLivraison() { return notesLivraison; }
    public void setNotesLivraison(String notesLivraison) { this.notesLivraison = notesLivraison; }
    public String getSignatureClient() { return signatureClient; }
    public void setSignatureClient(String signatureClient) { this.signatureClient = signatureClient; }
    public boolean isConfirmationQRCode() { return confirmationQRCode; }
    public void setConfirmationQRCode(boolean confirmationQRCode) { this.confirmationQRCode = confirmationQRCode; }
    public String getTokenConfirmation() { return tokenConfirmation; }
    public void setTokenConfirmation(String tokenConfirmation) { this.tokenConfirmation = tokenConfirmation; }
    public String getPreuveScanQR() { return preuveScanQR; }
    public void setPreuveScanQR(String preuveScanQR) { this.preuveScanQR = preuveScanQR; }
}
