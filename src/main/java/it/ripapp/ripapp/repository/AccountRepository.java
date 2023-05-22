package it.ripapp.ripapp.repository;


import it.ripapp.ripapp.entityUpdate.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByEmail(String email);

    AccountEntity findByIdtoken(String idtoken);


    List<AccountEntity> findAllByAgency_NameContainsOrAgency_EmailContains(String query, String query2);

}
