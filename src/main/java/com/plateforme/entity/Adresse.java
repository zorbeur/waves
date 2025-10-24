package com.plateforme.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Adresse {
    private String ligne1;
    private String ligne2;
    private String ville;
    private String codePostal;
    private String pays;

    public String getLigne1() { return ligne1; }
    public void setLigne1(String ligne1) { this.ligne1 = ligne1; }
    public String getLigne2() { return ligne2; }
    public void setLigne2(String ligne2) { this.ligne2 = ligne2; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }
}
