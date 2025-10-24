package com.plateforme.service;

import com.plateforme.entity.*;
import com.plateforme.entity.enums.StatutCommande;
import com.plateforme.repository.ClientRepository;
import com.plateforme.repository.CommandeRepository;
import com.plateforme.repository.VagueRepository;
import com.plateforme.email.EmailRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final VagueRepository vagueRepository;
    private final ClientRepository clientRepository;

    private final EmailService emailService;

    public CommandeService(CommandeRepository commandeRepository, VagueRepository vagueRepository, ClientRepository clientRepository, EmailService emailService) {
        this.commandeRepository = commandeRepository;
        this.vagueRepository = vagueRepository;
        this.clientRepository = clientRepository;
        this.emailService = emailService;
    }

    public Commande creerCommande(UUID vagueId, String clientEmail) {
        Vague vague = vagueRepository.findById(vagueId).orElseThrow();
        if (vague.getStatut() != null && !vague.getStatut().name().equals("ACTIVE")) {
            throw new IllegalStateException("Vague inactive");
        }
        Optional<Client> clientOpt = clientRepository.findByEmail(clientEmail);
        Client client = clientOpt.orElseThrow();
        Commande commande = new Commande();
        commande.setVague(vague);
        commande.setClient(client);
        commande.setNumeroCommande(UUID.randomUUID().toString());
        commande.setStatut(StatutCommande.EN_ATTENTE);
        commande.setSousTotal(BigDecimal.ZERO);
        commande.setFraisLivraison(BigDecimal.ZERO);
        commande.setTotal(BigDecimal.ZERO);
        Commande saved = commandeRepository.save(commande);

        // Email confirmation
        EmailRequest email = new EmailRequest();
        email.setTo(client.getEmail());
        email.setSubject("Confirmation de commande #" + saved.getNumeroCommande());
        email.setTemplate("emails/confirmation-commande");
        java.util.Map<String, Object> vars = new java.util.HashMap<>();
        vars.put("client", client);
        vars.put("commande", saved);
        email.setVariables(vars);
        emailService.envoyerEmail(email);

        return saved;
    }
}
