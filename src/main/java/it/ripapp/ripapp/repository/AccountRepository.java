package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entityUpdate.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    AccountEntity findByEmail(String email);

}
