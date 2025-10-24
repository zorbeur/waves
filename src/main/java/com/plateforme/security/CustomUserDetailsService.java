package com.plateforme.security;

import com.plateforme.entity.Utilisateur;
import com.plateforme.entity.enums.TypeUtilisateur;
import com.plateforme.repository.UtilisateurRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    public CustomUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        return new User(user.getEmail(), user.getMotDePasse(), user.isActif(), true, true, true, mapRoles(user.getType()));
    }

    private Collection<? extends GrantedAuthority> mapRoles(TypeUtilisateur type) {
        return switch (type) {
            case ADMIN -> List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
            case GERANT -> List.of(new SimpleGrantedAuthority("ROLE_GERANT"));
            case LIVREUR -> List.of(new SimpleGrantedAuthority("ROLE_LIVREUR"));
            case CLIENT -> List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
        };
    }
}
