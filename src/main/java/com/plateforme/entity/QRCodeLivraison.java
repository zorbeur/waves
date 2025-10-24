package com.plateforme.entity;

import com.plateforme.entity.enums.StatutQRCode;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "qrcode_livraison")
public class QRCodeLivraison extends BaseEntity {

    @OneToOne
    private Commande commande;

    @Column(unique = true)
    private String tokenUnique;

    @Lob
    private String qrCodeImage; // Base64

    private LocalDateTime dateGeneration;
    private LocalDateTime dateScan;

    @Enumerated(EnumType.STRING)
    private StatutQRCode statut;

    @ManyToOne
    private Livreur livreur;

    private String adresseScan; // JSON géolocalisation

    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }
    public String getTokenUnique() { return tokenUnique; }
    public void setTokenUnique(String tokenUnique) { this.tokenUnique = tokenUnique; }
    public String getQrCodeImage() { return qrCodeImage; }
    public void setQrCodeImage(String qrCodeImage) { this.qrCodeImage = qrCodeImage; }
    public LocalDateTime getDateGeneration() { return dateGeneration; }
    public void setDateGeneration(LocalDateTime dateGeneration) { this.dateGeneration = dateGeneration; }
    public LocalDateTime getDateScan() { return dateScan; }
    public void setDateScan(LocalDateTime dateScan) { this.dateScan = dateScan; }
    public StatutQRCode getStatut() { return statut; }
    public void setStatut(StatutQRCode statut) { this.statut = statut; }
    public Livreur getLivreur() { return livreur; }
    public void setLivreur(Livreur livreur) { this.livreur = livreur; }
    public String getAdresseScan() { return adresseScan; }
    public void setAdresseScan(String adresseScan) { this.adresseScan = adresseScan; }
}
