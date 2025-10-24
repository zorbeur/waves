package com.plateforme.service;

import com.plateforme.entity.Audit;
import com.plateforme.entity.enums.TypeAction;
import com.plateforme.entity.enums.TypeUtilisateur;
import com.plateforme.repository.AuditRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class AuditService {

    private final AuditRepository auditRepository;

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Async
    public void enregistrerAction(TypeAction typeAction, String tableConcernee,
                                  UUID enregistrementId, String anciennesValeurs,
                                  String nouvellesValeurs, String details) {
        Audit audit = new Audit();
        audit.setTypeAction(typeAction);
        audit.setTableConcernee(tableConcernee);
        audit.setEnregistrementId(enregistrementId);
        audit.setAnciennesValeurs(anciennesValeurs);
        audit.setNouvellesValeurs(nouvellesValeurs);
        // Dans une vraie app, récupérer l'utilisateur courant
        audit.setUtilisateurId(null);
        audit.setTypeUtilisateur(TypeUtilisateur.ADMIN);
        audit.setAdresseIp("0.0.0.0");
        audit.setUserAgent("unknown");
        audit.setDetails(details);

        auditRepository.save(audit);
    }

    public Page<Audit> getAuditsFiltres(TypeAction typeAction, LocalDateTime startDate, LocalDateTime endDate, int page, int size) {
        // Simplifié: pas de Specification avancée ici
        return auditRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateHeure")));
    }
}
