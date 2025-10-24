package com.plateforme.repository;

import com.plateforme.entity.QRCodeLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QRCodeLivraisonRepository extends JpaRepository<QRCodeLivraison, UUID> {
    Optional<QRCodeLivraison> findByTokenUnique(String tokenUnique);
}
