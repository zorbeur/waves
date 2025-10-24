package com.plateforme.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

    private String nomComplet;
    private String email;
    private String telephone;

    @Embedded
    private Adresse adresseLivraison;

    private boolean inscritNewsletter = false;

    @CreationTimestamp
    private LocalDateTime dateCreation;

    public String getNomComplet() { return nomComplet; }
    public void setNomComplet(String nomComplet) { this.nomComplet = nomComplet; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public Adresse getAdresseLivraison() { return adresseLivraison; }
    public void setAdresseLivraison(Adresse adresseLivraison) { this.adresseLivraison = adresseLivraison; }
    public boolean isInscritNewsletter() { return inscritNewsletter; }
    public void setInscritNewsletter(boolean inscritNewsletter) { this.inscritNewsletter = inscritNewsletter; }
    public LocalDateTime getDateCreation() { return dateCreation; }
}
