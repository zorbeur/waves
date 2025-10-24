package com.plateforme.controller;

import com.plateforme.dto.livreur.ConfirmationLivraisonRequest;
import com.plateforme.dto.livreur.ScanQRRequest;
import com.plateforme.entity.Livraison;
import com.plateforme.entity.enums.StatutLivraison;
import com.plateforme.repository.LivraisonRepository;
import com.plateforme.service.QRCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/livreur")
@PreAuthorize("hasAnyRole('LIVREUR','ADMIN','GERANT')")
public class LivreurController {

    private final LivraisonRepository livraisonRepository;
    private final QRCodeService qrCodeService;

    public LivreurController(LivraisonRepository livraisonRepository, QRCodeService qrCodeService) {
        this.livraisonRepository = livraisonRepository;
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("/livraisons")
    public ResponseEntity<List<Livraison>> getLivraisonsAssignees() {
        return ResponseEntity.ok(livraisonRepository.findAll());
    }

    @PostMapping("/livraisons/{id}/scanner-qr")
    public ResponseEntity<Livraison> scannerQRCode(@PathVariable UUID id, @RequestBody ScanQRRequest request) {
        // id de livraison ici non utilisé par la démo; on se base sur le token
        Livraison livraison = qrCodeService.scannerQRCode(request.getToken(), null, request.getGeolocalisationJson());
        return ResponseEntity.ok(livraison);
    }

    @PutMapping("/livraisons/{id}/statut")
    public ResponseEntity<Livraison> updateStatutLivraison(@PathVariable UUID id, @RequestParam StatutLivraison statut) {
        Livraison livraison = livraisonRepository.findById(id).orElseThrow();
        livraison.setStatut(statut);
        return ResponseEntity.ok(livraisonRepository.save(livraison));
    }

    @PostMapping("/livraisons/{id}/confirmer")
    public ResponseEntity<Livraison> confirmerLivraison(@PathVariable UUID id, @RequestBody ConfirmationLivraisonRequest request) {
        Livraison livraison = livraisonRepository.findById(id).orElseThrow();
        livraison.setSignatureClient(request.getSignatureClient());
        livraison.setNotesLivraison(request.getNotes());
        return ResponseEntity.ok(livraisonRepository.save(livraison));
    }
}
