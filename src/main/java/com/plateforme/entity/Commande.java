package com.plateforme.entity;

import com.plateforme.entity.enums.MethodePaiement;
import com.plateforme.entity.enums.StatutCommande;
import com.plateforme.entity.enums.StatutPaiement;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commandes")
public class Commande extends BaseEntity {

    @Column(unique = true)
    private String numeroCommande;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Vague vague;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> lignesCommande;

    @Column(precision = 10, scale = 2)
    private BigDecimal sousTotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal fraisLivraison;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatutCommande statut;

    @Embedded
    private Adresse adresseLivraison;

    @Enumerated(EnumType.STRING)
    private MethodePaiement methodePaiement;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement;

    private String notesClient;

    @CreationTimestamp
    private LocalDateTime dateCommande;

    public String getNumeroCommande() { return numeroCommande; }
    public void setNumeroCommande(String numeroCommande) { this.numeroCommande = numeroCommande; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public Vague getVague() { return vague; }
    public void setVague(Vague vague) { this.vague = vague; }
    public List<LigneCommande> getLignesCommande() { return lignesCommande; }
    public void setLignesCommande(List<LigneCommande> lignesCommande) { this.lignesCommande = lignesCommande; }
    public BigDecimal getSousTotal() { return sousTotal; }
    public void setSousTotal(BigDecimal sousTotal) { this.sousTotal = sousTotal; }
    public BigDecimal getFraisLivraison() { return fraisLivraison; }
    public void setFraisLivraison(BigDecimal fraisLivraison) { this.fraisLivraison = fraisLivraison; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public StatutCommande getStatut() { return statut; }
    public void setStatut(StatutCommande statut) { this.statut = statut; }
    public Adresse getAdresseLivraison() { return adresseLivraison; }
    public void setAdresseLivraison(Adresse adresseLivraison) { this.adresseLivraison = adresseLivraison; }
    public MethodePaiement getMethodePaiement() { return methodePaiement; }
    public void setMethodePaiement(MethodePaiement methodePaiement) { this.methodePaiement = methodePaiement; }
    public StatutPaiement getStatutPaiement() { return statutPaiement; }
    public void setStatutPaiement(StatutPaiement statutPaiement) { this.statutPaiement = statutPaiement; }
    public String getNotesClient() { return notesClient; }
    public void setNotesClient(String notesClient) { this.notesClient = notesClient; }
    public LocalDateTime getDateCommande() { return dateCommande; }
}
