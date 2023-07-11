package it.ripapp.ripapp.repository;


import it.ripapp.ripapp.entityUpdate.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Page<AccountEntity> findAll(Pageable pageable);

    AccountEntity findByEmail(String email);

    AccountEntity findByIdtoken(String idtoken);

    boolean existsAccountEntityByEmail(String email);


    List<AccountEntity> findAllByAgency_NameContainsOrAgency_EmailContains(String query, String query2);

}
