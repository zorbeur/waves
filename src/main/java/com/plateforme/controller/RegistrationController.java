package com.plateforme.controller;

import com.plateforme.dto.admin.CreateUserRequest;
import com.plateforme.entity.Client;
import com.plateforme.entity.Utilisateur;
import com.plateforme.entity.enums.TypeUtilisateur;
import com.plateforme.repository.ClientRepository;
import com.plateforme.repository.UtilisateurRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/inscription")
public class RegistrationController {

    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UtilisateurRepository utilisateurRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Inscription client - rôle automatique CLIENT
    @PostMapping("/client")
    public ResponseEntity<Client> registerClient(@Valid @RequestBody CreateUserRequest req) {
        Client c = new Client();
        c.setNomComplet(req.getNom());
        c.setEmail(req.getEmail());
        // Le client n'est pas un Utilisateur de sécurité; si souhaité, on peut créer un Utilisateur associé
        return ResponseEntity.ok(clientRepository.save(c));
    }

    // Inscription utilisateur standard côté public -> type CLIENT par défaut
    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> registerUtilisateur(@Valid @RequestBody CreateUserRequest req) {
        Utilisateur u = new Utilisateur();
        u.setNom(req.getNom());
        u.setEmail(req.getEmail());
        u.setMotDePasse(passwordEncoder.encode(req.getMotDePasse()));
        u.setType(TypeUtilisateur.CLIENT);
        return ResponseEntity.ok(utilisateurRepository.save(u));
    }
}
