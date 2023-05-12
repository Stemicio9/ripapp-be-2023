package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entities.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgencyRepository extends JpaRepository<it.ripapp.ripapp.entityUpdate.AgencyEntity, UUID> {
    AgencyEntity getAgencyByEmail(String email);
}
