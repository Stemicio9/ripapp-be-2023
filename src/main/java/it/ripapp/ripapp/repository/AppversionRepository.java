package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.EntityUpdate.Appversions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppversionRepository extends JpaRepository<Appversions, String> {
}
