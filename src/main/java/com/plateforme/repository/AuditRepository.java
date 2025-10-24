package com.plateforme.repository;

import com.plateforme.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditRepository extends JpaRepository<Audit, UUID>, JpaSpecificationExecutor<Audit> {
}
