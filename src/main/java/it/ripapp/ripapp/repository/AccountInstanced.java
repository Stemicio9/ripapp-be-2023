package it.ripapp.ripapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInstanced extends JpaRepository<it.ripapp.ripapp.entityUpdate.AccountInstanceid, String> {
}