package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.EntityUpdate.AccountEntity;
import it.ripapp.ripapp.EntityUpdate.PhoneBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneBookRepository extends JpaRepository<PhoneBookEntity, AccountEntity> {
}
