package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entityUpdate.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    AccountEntity findByEmail(String email);


    List<AccountEntity> findAllByAgency_NameContainsOrAgency_EmailContains(String query, String query2);

}
