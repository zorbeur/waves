package com.plateforme.controller;

import com.plateforme.dto.admin.CreateUserRequest;
import com.plateforme.entity.Admin;
import com.plateforme.entity.Gerant;
import com.plateforme.entity.Livreur;
import com.plateforme.entity.Utilisateur;
import com.plateforme.entity.enums.TypeUtilisateur;
import com.plateforme.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import com.plateforme.service.EmailService;
import com.plateforme.email.EmailRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AdminController(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @PostMapping("/gerants")
    public ResponseEntity<Gerant> createGerant(@Valid @RequestBody CreateUserRequest request) {
        Gerant g = new Gerant();
        g.setNom(request.getNom());
        g.setEmail(request.getEmail());
        g.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        g.setType(TypeUtilisateur.GERANT);
        Gerant saved = utilisateurRepository.save(g);
        EmailRequest email = new EmailRequest();
        email.setTo(saved.getEmail());
        email.setSubject("Bienvenue - Accès Gérant");
        email.setTemplate("emails/simple");
        java.util.Map<String, Object> vars = new java.util.HashMap<>();
        vars.put("titre", "Bienvenue dans l'équipe");
        vars.put("message", "Votre compte gérant a été créé.");
        email.setVariables(vars);
        emailService.envoyerEmail(email);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/livreurs")
    public ResponseEntity<Livreur> createLivreur(@Valid @RequestBody CreateUserRequest request) {
        Livreur l = new Livreur();
        l.setNom(request.getNom());
        l.setEmail(request.getEmail());
        l.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        l.setType(TypeUtilisateur.LIVREUR);
        Livreur saved = utilisateurRepository.save(l);
        EmailRequest email = new EmailRequest();
        email.setTo(saved.getEmail());
        email.setSubject("Bienvenue - Accès Livreur");
        email.setTemplate("emails/simple");
        java.util.Map<String, Object> vars = new java.util.HashMap<>();
        vars.put("titre", "Bienvenue dans l'équipe");
        vars.put("message", "Votre compte livreur a été créé.");
        email.setVariables(vars);
        emailService.envoyerEmail(email);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/utilisateurs")
    public ResponseEntity<List<Utilisateur>> getUsers() {
        return ResponseEntity.ok(utilisateurRepository.findAll());
    }
}
