package it.ripapp.ripapp.repository;


import it.ripapp.ripapp.entityUpdate.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
}
