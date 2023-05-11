package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.jooqgen.tables.DemiseMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DemiseMatchRepository extends JpaRepository<DemiseMatch, UUID> {


}
