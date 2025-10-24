package com.plateforme.controller;

import com.plateforme.entity.Commande;
import com.plateforme.entity.Produit;
import com.plateforme.entity.Vague;
import com.plateforme.repository.ProduitRepository;
import com.plateforme.service.CommandeService;
import com.plateforme.service.VagueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final VagueService vagueService;
    private final ProduitRepository produitRepository;
    private final CommandeService commandeService;

    public ClientController(VagueService vagueService, ProduitRepository produitRepository, CommandeService commandeService) {
        this.vagueService = vagueService;
        this.produitRepository = produitRepository;
        this.commandeService = commandeService;
    }

    @GetMapping("/vagues/actives")
    public ResponseEntity<List<Vague>> getVaguesActives() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vagues/{vagueId}/produits")
    public ResponseEntity<List<Produit>> getProduitsVague(@PathVariable UUID vagueId) {
        return ResponseEntity.ok(produitRepository.findAll());
    }

    @PostMapping("/commandes")
    public ResponseEntity<Commande> creerCommande(@RequestParam UUID vagueId, @RequestParam String clientEmail) {
        return ResponseEntity.ok(commandeService.creerCommande(vagueId, clientEmail));
    }
}
