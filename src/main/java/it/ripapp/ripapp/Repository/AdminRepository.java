package it.ripapp.ripapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<it.ripapp.ripapp.entityUpdate.AdminEntity, UUID> {
}
