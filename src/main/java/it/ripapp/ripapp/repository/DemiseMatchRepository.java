package it.ripapp.ripapp.repository;


import it.ripapp.ripapp.entityUpdate.DemiseMatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DemiseMatchRepository extends JpaRepository<DemiseMatchEntity, Long> {}
