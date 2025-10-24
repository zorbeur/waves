package com.plateforme.entity;

import com.plateforme.entity.enums.FormatExport;
import com.plateforme.entity.enums.TypeRapport;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rapports")
public class Rapport extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TypeRapport type;

    private String titre;

    @Lob
    private String contenu; // JSON

    @ManyToOne
    private Vague vague;

    private LocalDateTime periodeDebut;
    private LocalDateTime periodeFin;

    private LocalDateTime dateGeneration;

    @ManyToOne
    private Utilisateur createur;

    @Enumerated(EnumType.STRING)
    private FormatExport formatExport;

    public TypeRapport getType() { return type; }
    public void setType(TypeRapport type) { this.type = type; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public Vague getVague() { return vague; }
    public void setVague(Vague vague) { this.vague = vague; }
    public LocalDateTime getPeriodeDebut() { return periodeDebut; }
    public void setPeriodeDebut(LocalDateTime periodeDebut) { this.periodeDebut = periodeDebut; }
    public LocalDateTime getPeriodeFin() { return periodeFin; }
    public void setPeriodeFin(LocalDateTime periodeFin) { this.periodeFin = periodeFin; }
    public LocalDateTime getDateGeneration() { return dateGeneration; }
    public void setDateGeneration(LocalDateTime dateGeneration) { this.dateGeneration = dateGeneration; }
    public Utilisateur getCreateur() { return createur; }
    public void setCreateur(Utilisateur createur) { this.createur = createur; }
    public FormatExport getFormatExport() { return formatExport; }
    public void setFormatExport(FormatExport formatExport) { this.formatExport = formatExport; }
}
