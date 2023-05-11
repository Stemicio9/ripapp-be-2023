package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entityUpdate.Appversions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppversionRepository extends JpaRepository<Appversions, String> {
}
