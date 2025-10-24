package com.plateforme.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.plateforme.entity.Commande;
import com.plateforme.entity.Livraison;
import com.plateforme.entity.QRCodeLivraison;
import com.plateforme.entity.enums.StatutLivraison;
import com.plateforme.entity.enums.StatutQRCode;
import com.plateforme.repository.CommandeRepository;
import com.plateforme.repository.LivraisonRepository;
import com.plateforme.repository.QRCodeLivraisonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
@Transactional
public class QRCodeService {

    private final CommandeRepository commandeRepository;
    private final QRCodeLivraisonRepository qrCodeRepository;
    private final LivraisonRepository livraisonRepository;

    public QRCodeService(CommandeRepository commandeRepository, QRCodeLivraisonRepository qrCodeRepository, LivraisonRepository livraisonRepository) {
        this.commandeRepository = commandeRepository;
        this.qrCodeRepository = qrCodeRepository;
        this.livraisonRepository = livraisonRepository;
    }

    public QRCodeLivraison genererQRCodePourCommande(UUID commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        String token = UUID.randomUUID().toString();
        String qrCodeData = String.format("{\"commandeId\":\"%s\",\"token\":\"%s\",\"timestamp\":\"%s\"}",
            commandeId, token, LocalDateTime.now());

        String qrCodeImage = genererImageQRCode(qrCodeData);

        QRCodeLivraison qrCode = new QRCodeLivraison();
        qrCode.setCommande(commande);
        qrCode.setTokenUnique(hashToken(token));
        qrCode.setQrCodeImage(qrCodeImage);
        qrCode.setDateGeneration(LocalDateTime.now());
        qrCode.setStatut(StatutQRCode.GENERE);

        return qrCodeRepository.save(qrCode);
    }

    public Livraison scannerQRCode(String token, UUID livreurId, String geolocalisation) {
        QRCodeLivraison qrCode = qrCodeRepository.findByTokenUnique(hashToken(token))
            .orElseThrow(() -> new RuntimeException("QR code invalide"));

        if (qrCode.getStatut() == StatutQRCode.SCANNE) {
            throw new RuntimeException("QR code déjà utilisé");
        }
        if (qrCode.getDateGeneration().isBefore(LocalDateTime.now().minusHours(24))) {
            throw new RuntimeException("QR code expiré");
        }

        Livraison livraison = livraisonRepository.findByCommande(qrCode.getCommande())
            .orElseThrow(() -> new RuntimeException("Livraison non trouvée"));

        qrCode.setStatut(StatutQRCode.SCANNE);
        qrCode.setDateScan(LocalDateTime.now());
        qrCode.setAdresseScan(geolocalisation);

        livraison.setConfirmationQRCode(true);
        livraison.setStatut(StatutLivraison.LIVREE);
        livraison.setDateLivraisonReelle(LocalDateTime.now());
        livraison.setTokenConfirmation(token);

        return livraisonRepository.save(livraison);
    }

    private String genererImageQRCode(String data) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 300, 300);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return Base64.getEncoder().encodeToString(pngOutputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Erreur génération QR code", e);
        }
    }

    private String hashToken(String token) {
        return Integer.toHexString(token.hashCode());
    }
}
