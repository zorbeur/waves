package com.plateforme.config;

import com.plateforme.entity.Admin;
import com.plateforme.entity.enums.TypeUtilisateur;
import com.plateforme.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedAdmin(UtilisateurRepository utilisateurRepository, PasswordEncoder encoder) {
        return args -> utilisateurRepository.findByEmail("admin@local").orElseGet(() -> {
            Admin admin = new Admin();
            admin.setNom("Admin");
            admin.setEmail("admin@local");
            admin.setMotDePasse(encoder.encode("admin123"));
            admin.setType(TypeUtilisateur.ADMIN);
            return utilisateurRepository.save(admin);
        });
    }
}
