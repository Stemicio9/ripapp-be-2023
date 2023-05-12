package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public interface AgencyRepository extends JpaRepository<it.ripapp.ripapp.entityUpdate.AgencyEntity, UUID> {
    AgencyEntity getAgencyByEmail(String email);

    AgencyEntity findByEmail(String email);

    List<AgencyEntity> findAllByEmail(String email);

    it.ripapp.ripapp.entities.AgencyEntity getAgencyByID(UUID agencyid);
}
