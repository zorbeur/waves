package com.plateforme.entity;

import com.plateforme.entity.enums.TypeAction;
import com.plateforme.entity.enums.TypeUtilisateur;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audits")
public class Audit extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TypeAction typeAction;

    private String tableConcernee;
    private UUID enregistrementId;

    @Lob
    private String anciennesValeurs; // JSON

    @Lob
    private String nouvellesValeurs; // JSON

    private UUID utilisateurId;

    @Enumerated(EnumType.STRING)
    private TypeUtilisateur typeUtilisateur;

    private String adresseIp;
    private String userAgent;

    @CreationTimestamp
    private LocalDateTime dateHeure;

    private String details;

    public TypeAction getTypeAction() { return typeAction; }
    public void setTypeAction(TypeAction typeAction) { this.typeAction = typeAction; }
    public String getTableConcernee() { return tableConcernee; }
    public void setTableConcernee(String tableConcernee) { this.tableConcernee = tableConcernee; }
    public UUID getEnregistrementId() { return enregistrementId; }
    public void setEnregistrementId(UUID enregistrementId) { this.enregistrementId = enregistrementId; }
    public String getAnciennesValeurs() { return anciennesValeurs; }
    public void setAnciennesValeurs(String anciennesValeurs) { this.anciennesValeurs = anciennesValeurs; }
    public String getNouvellesValeurs() { return nouvellesValeurs; }
    public void setNouvellesValeurs(String nouvellesValeurs) { this.nouvellesValeurs = nouvellesValeurs; }
    public UUID getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(UUID utilisateurId) { this.utilisateurId = utilisateurId; }
    public TypeUtilisateur getTypeUtilisateur() { return typeUtilisateur; }
    public void setTypeUtilisateur(TypeUtilisateur typeUtilisateur) { this.typeUtilisateur = typeUtilisateur; }
    public String getAdresseIp() { return adresseIp; }
    public void setAdresseIp(String adresseIp) { this.adresseIp = adresseIp; }
    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
    public LocalDateTime getDateHeure() { return dateHeure; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
