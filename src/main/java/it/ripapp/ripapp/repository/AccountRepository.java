package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.bll.Lang;
import it.ripapp.ripapp.entities.ContactEntity;
import it.ripapp.ripapp.entityUpdate.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<it.ripapp.ripapp.entityUpdate.AccountEntity, UUID> {
    AccountEntity getAccountByEmail(String email);

    void updateUserLang(UUID accountid, Lang lang);

    boolean insertAccount(AccountEntity account);

    List<String> getAllPrefixes();


    void updatePhoneBookSync(UUID accountid, List<ContactEntity> hashed, Integer offset);
}
