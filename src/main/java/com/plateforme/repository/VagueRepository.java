package com.plateforme.repository;

import com.plateforme.entity.Vague;
import com.plateforme.entity.enums.StatutVague;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface VagueRepository extends JpaRepository<Vague, UUID>, JpaSpecificationExecutor<Vague> {

    List<Vague> findByStatut(StatutVague statut);

    List<Vague> findByDateDebutBeforeAndDateFinAfter(LocalDateTime now1, LocalDateTime now2);

    List<Vague> findByDateFinBeforeAndStatut(LocalDateTime date, StatutVague statut);

    @Query("SELECT v FROM Vague v WHERE v.dateDebut > :now ORDER BY v.dateDebut ASC")
    List<Vague> findVaguesAVenir(@Param("now") LocalDateTime now);
}
