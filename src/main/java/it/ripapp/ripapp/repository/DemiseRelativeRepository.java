package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entityUpdate.DemiseRelative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DemiseRelativeRepository extends JpaRepository<DemiseRelative, UUID> {
}
