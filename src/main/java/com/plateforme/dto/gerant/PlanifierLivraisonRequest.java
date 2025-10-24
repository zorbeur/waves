package com.plateforme.dto.gerant;

import java.time.LocalDateTime;
import java.util.UUID;

public class PlanifierLivraisonRequest {
    private UUID commandeId;
    private UUID livreurId;
    private LocalDateTime datePlanification;

    public UUID getCommandeId() { return commandeId; }
    public void setCommandeId(UUID commandeId) { this.commandeId = commandeId; }
    public UUID getLivreurId() { return livreurId; }
    public void setLivreurId(UUID livreurId) { this.livreurId = livreurId; }
    public LocalDateTime getDatePlanification() { return datePlanification; }
    public void setDatePlanification(LocalDateTime datePlanification) { this.datePlanification = datePlanification; }
}
