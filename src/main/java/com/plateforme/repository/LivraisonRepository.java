package com.plateforme.repository;

import com.plateforme.entity.Commande;
import com.plateforme.entity.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, UUID> {
    Optional<Livraison> findByCommande(Commande commande);
}
