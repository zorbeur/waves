package com.plateforme.repository;

import com.plateforme.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, UUID> {
}
