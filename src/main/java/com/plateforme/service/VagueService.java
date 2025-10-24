package com.plateforme.service;

import com.plateforme.entity.Vague;
import com.plateforme.entity.enums.StatutVague;
import com.plateforme.repository.VagueRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class VagueService {

    private final VagueRepository vagueRepository;

    public VagueService(VagueRepository vagueRepository) {
        this.vagueRepository = vagueRepository;
    }

    public Vague createVague(String nom, String description, LocalDateTime debut, LocalDateTime fin) {
        Vague vague = new Vague();
        vague.setNom(nom);
        vague.setDescription(description);
        vague.setDateDebut(debut);
        vague.setDateFin(fin);
        vague.setStatut(StatutVague.PLANIFIEE);
        return vagueRepository.save(vague);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void verifierExpirationVagues() {
        List<Vague> vaguesExpirees = vagueRepository.findByDateFinBeforeAndStatut(LocalDateTime.now(), StatutVague.ACTIVE);
        for (Vague vague : vaguesExpirees) {
            vague.setStatut(StatutVague.TERMINEE);
            vagueRepository.save(vague);
        }
    }

    @Cacheable(value = "vaguesAVenir")
    public List<Vague> getVaguesAVenir() {
        return vagueRepository.findVaguesAVenir(LocalDateTime.now());
    }

    @CacheEvict(value = {"vaguesAVenir"}, allEntries = true)
    public void evictCaches() {}
}
