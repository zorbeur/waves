package com.plateforme.repository;

import com.plateforme.entity.Commande;
import com.plateforme.entity.Vague;
import com.plateforme.entity.enums.StatutCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, UUID> {

    Optional<Commande> findByNumeroCommande(String numeroCommande);

    List<Commande> findByVagueAndStatut(Vague vague, StatutCommande statut);

    @Query("SELECT c FROM Commande c WHERE c.client.email = :email AND c.numeroCommande = :numero")
    Optional<Commande> findByNumeroAndEmail(@Param("numero") String numero, @Param("email") String email);
}
