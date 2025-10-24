package com.plateforme.service;

import com.plateforme.entity.Client;
import com.plateforme.entity.Commande;
import com.plateforme.entity.Vague;
import com.plateforme.entity.enums.StatutVague;
import com.plateforme.repository.ClientRepository;
import com.plateforme.repository.CommandeRepository;
import com.plateforme.repository.VagueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandeServiceTest {

    @Mock
    private CommandeRepository commandeRepository;

    @Mock
    private VagueRepository vagueRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private CommandeService commandeService;

    @Test
    void creerCommande_ShouldReturnCommande() {
        UUID vagueId = UUID.randomUUID();
        String email = "test@test.com";

        Vague vague = new Vague();
        vague.setStatut(StatutVague.ACTIVE);

        Client client = new Client();
        client.setEmail(email);

        when(vagueRepository.findById(vagueId)).thenReturn(Optional.of(vague));
        when(clientRepository.findByEmail(email)).thenReturn(Optional.of(client));
        when(commandeRepository.save(any(Commande.class))).thenAnswer(invocation -> {
            Commande cmd = invocation.getArgument(0);
            return cmd; // id géré par UUID
        });

        Commande result = commandeService.creerCommande(vagueId, email);

        assertNotNull(result);
        assertEquals(email, result.getClient().getEmail());
        verify(commandeRepository, times(1)).save(any(Commande.class));
    }
}
