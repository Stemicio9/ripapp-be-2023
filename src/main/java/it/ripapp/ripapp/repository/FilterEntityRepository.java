package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.EntityUpdate.FilterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilterEntityRepository extends JpaRepository<FilterEntity, Long> {
}
