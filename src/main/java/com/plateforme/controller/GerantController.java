package com.plateforme.controller;

import com.plateforme.dto.gerant.CreatePackageRequest;
import com.plateforme.dto.gerant.CreateProduitRequest;
import com.plateforme.dto.gerant.CreatePromotionRequest;
import com.plateforme.dto.gerant.CreateVagueRequest;
import com.plateforme.dto.gerant.PlanifierLivraisonRequest;
import com.plateforme.entity.*;
import com.plateforme.repository.*;
import com.plateforme.service.VagueService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gerant")
@PreAuthorize("hasAnyRole('GERANT','ADMIN')")
public class GerantController {

    private final VagueService vagueService;
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final PromotionRepository promotionRepository;
    private final PackageProduitRepository packageProduitRepository;
    private final VagueRepository vagueRepository;
    private final CommandeRepository commandeRepository;
    private final LivraisonRepository livraisonRepository;

    public GerantController(VagueService vagueService,
                            ProduitRepository produitRepository,
                            CategorieRepository categorieRepository,
                            PromotionRepository promotionRepository,
                            PackageProduitRepository packageProduitRepository,
                            VagueRepository vagueRepository,
                            CommandeRepository commandeRepository,
                            LivraisonRepository livraisonRepository) {
        this.vagueService = vagueService;
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.promotionRepository = promotionRepository;
        this.packageProduitRepository = packageProduitRepository;
        this.vagueRepository = vagueRepository;
        this.commandeRepository = commandeRepository;
        this.livraisonRepository = livraisonRepository;
    }

    @PostMapping("/vagues")
    public ResponseEntity<Vague> createVague(@RequestBody CreateVagueRequest request) {
        Vague vague = vagueService.createVague(request.getNom(), request.getDescription(), request.getDateDebut(), request.getDateFin());
        return ResponseEntity.ok(vague);
    }

    @PutMapping("/vagues/{id}/produits")
    public ResponseEntity<Vague> addProduitsToVague(@PathVariable UUID id, @RequestBody List<UUID> produitIds) {
        Vague vague = vagueRepository.findById(id).orElseThrow();
        List<Produit> produits = produitRepository.findAllById(produitIds);
        vague.setProduits(produits);
        return ResponseEntity.ok(vagueRepository.save(vague));
    }

    @PostMapping("/produits")
    public ResponseEntity<Produit> createProduit(@RequestBody CreateProduitRequest request) {
        Produit p = new Produit();
        p.setNom(request.getNom());
        p.setDescription(request.getDescription());
        p.setPrixOriginal(request.getPrixOriginal());
        p.setPrixActuel(request.getPrixActuel());
        p.setCategorie(categorieRepository.findById(request.getCategorieId()).orElse(null));
        return ResponseEntity.ok(produitRepository.save(p));
    }

    @PostMapping("/promotions")
    public ResponseEntity<Promotion> createPromotion(@RequestBody CreatePromotionRequest request) {
        Promotion promo = new Promotion();
        promo.setProduit(produitRepository.findById(request.getProduitId()).orElseThrow());
        promo.setType(request.getType());
        promo.setValeur(request.getValeur());
        promo.setDateDebut(request.getDateDebut());
        promo.setDateFin(request.getDateFin());
        return ResponseEntity.ok(promotionRepository.save(promo));
    }

    @PostMapping("/packages")
    public ResponseEntity<PackageProduit> createPackage(@RequestBody CreatePackageRequest request) {
        PackageProduit pack = new PackageProduit();
        pack.setNom(request.getNom());
        pack.setDescription(request.getDescription());
        pack.setPrixPackage(request.getPrixPackage());
        pack.setProduits(produitRepository.findAllById(request.getProduitIds()));
        return ResponseEntity.ok(packageProduitRepository.save(pack));
    }

    @GetMapping("/vagues/{vagueId}/rapport")
    public ResponseEntity<String> genererRapportVague(@PathVariable UUID vagueId) {
        // Placeholder: renvoyer une chaîne pour compilation
        return ResponseEntity.ok("rapport");
    }

    @PostMapping("/livraisons/planifier")
    public ResponseEntity<Livraison> planifierLivraison(@RequestBody PlanifierLivraisonRequest request) {
        Commande cmd = commandeRepository.findById(request.getCommandeId()).orElseThrow();
        Livraison livraison = new Livraison();
        livraison.setCommande(cmd);
        livraison.setDatePlanification(request.getDatePlanification());
        return ResponseEntity.ok(livraisonRepository.save(livraison));
    }

    @GetMapping("/commandes")
    public ResponseEntity<List<Commande>> getCommandes(@RequestParam(required = false) UUID vagueId) {
        if (vagueId == null) return ResponseEntity.ok(commandeRepository.findAll());
        return ResponseEntity.ok(commandeRepository.findAll());
    }
}
