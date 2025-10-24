package com.plateforme.repository;

import com.plateforme.entity.PackageProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackageProduitRepository extends JpaRepository<PackageProduit, UUID> {
}
