package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.EntityUpdate.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
}
