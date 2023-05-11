package it.ripapp.ripapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgencyRepository extends JpaRepository<it.ripapp.ripapp.entityUpdate.AgencyEntity, UUID> {
}
