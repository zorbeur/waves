package com.plateforme.controller;

import com.plateforme.entity.Categorie;
import com.plateforme.entity.Produit;
import com.plateforme.entity.Vague;
import com.plateforme.repository.CategorieRepository;
import org.springframework.cache.annotation.Cacheable;
import com.plateforme.repository.ProduitRepository;
import com.plateforme.repository.VagueRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final VagueRepository vagueRepository;
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    public PublicController(VagueRepository vagueRepository, ProduitRepository produitRepository, CategorieRepository categorieRepository) {
        this.vagueRepository = vagueRepository;
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("/vagues/a-venir")
    @Cacheable("vaguesAVenir")
    public ResponseEntity<List<Vague>> getVaguesAVenir() {
        return ResponseEntity.ok(vagueRepository.findVaguesAVenir(LocalDateTime.now()));
    }

    @GetMapping("/produits/vedettes")
    public ResponseEntity<List<Produit>> getProduitsVedettes() {
        return ResponseEntity.ok(produitRepository.findAll());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Categorie>> getCategories() {
        return ResponseEntity.ok(categorieRepository.findAll());
    }
}
