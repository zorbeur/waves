package com.plateforme.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_logs")
public class EmailLog extends BaseEntity {
    private String destinataire;
    private String template;
    private String sujet;

    @CreationTimestamp
    private LocalDateTime dateEnvoi;
    private boolean envoyeAvecSucces;
    private String erreurMessage;

    private boolean ouvert = false;
    private LocalDateTime dateOuverture;
    private int nombreClics = 0;

    private String messageId;

    public String getDestinataire() { return destinataire; }
    public void setDestinataire(String destinataire) { this.destinataire = destinataire; }
    public String getTemplate() { return template; }
    public void setTemplate(String template) { this.template = template; }
    public String getSujet() { return sujet; }
    public void setSujet(String sujet) { this.sujet = sujet; }
    public LocalDateTime getDateEnvoi() { return dateEnvoi; }
    public boolean isEnvoyeAvecSucces() { return envoyeAvecSucces; }
    public void setEnvoyeAvecSucces(boolean envoyeAvecSucces) { this.envoyeAvecSucces = envoyeAvecSucces; }
    public String getErreurMessage() { return erreurMessage; }
    public void setErreurMessage(String erreurMessage) { this.erreurMessage = erreurMessage; }
    public boolean isOuvert() { return ouvert; }
    public void setOuvert(boolean ouvert) { this.ouvert = ouvert; }
    public LocalDateTime getDateOuverture() { return dateOuverture; }
    public void setDateOuverture(LocalDateTime dateOuverture) { this.dateOuverture = dateOuverture; }
    public int getNombreClics() { return nombreClics; }
    public void setNombreClics(int nombreClics) { this.nombreClics = nombreClics; }
    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
}
