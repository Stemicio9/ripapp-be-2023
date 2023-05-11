package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entityUpdate.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityEntityRepository extends JpaRepository<CityEntity, UUID> {
}
