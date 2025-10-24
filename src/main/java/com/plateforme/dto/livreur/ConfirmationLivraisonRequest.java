package com.plateforme.dto.livreur;

public class ConfirmationLivraisonRequest {
    private String signatureClient;
    private String notes;

    public String getSignatureClient() { return signatureClient; }
    public void setSignatureClient(String signatureClient) { this.signatureClient = signatureClient; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
