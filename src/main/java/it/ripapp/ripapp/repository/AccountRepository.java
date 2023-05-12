package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.bll.Kinship;
import it.ripapp.ripapp.bll.Lang;
import it.ripapp.ripapp.entities.ContactEntity;
import it.ripapp.ripapp.entities.DemiseEntity;
import it.ripapp.ripapp.entities.IEntity;
import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.entityUpdate.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<it.ripapp.ripapp.entityUpdate.AccountEntity, UUID> {




    DemiseEntity getUnreadDemisesAndMarkRead(UUID userid, Integer offset);

    AccountEntity findByEmail(String email);

    boolean insertAccount(AccountEntity account);

     default AccountEntity getAccountByID(UUID userid){
        AccountEntity account = findById(userid).orElse(null);
        return account;
    }

    default List<CityEntity> getCitiesByAccount(UUID userid){
        AccountEntity account = findById(userid).orElse(null);
        return account.getCities();
    }


    default void updateUserByID(UUID userid, AccountEntity accountEntity){
        AccountEntity account = findById(userid).orElse(null);
        account.setAccountid(accountEntity.getAccountid());
        account.setName(accountEntity.getName());
        account.setSurname(accountEntity.getSurname());
        account.setEmail(accountEntity.getEmail());
        account.setPrefix(accountEntity.getPrefix());
        account.setPhone(accountEntity.getPhone());
        account.setPhonehashed(accountEntity.getPhonehashed());
        account.setNotif(accountEntity.getNotif());
        account.setCity(accountEntity.getCity());
        account.setEnabled(accountEntity.getEnabled());
        account.setPhotourl(accountEntity.getPhotourl());
        account.setLang(accountEntity.getLang());
        account.setIdtoken(accountEntity.getIdtoken());
        account.setKinship(accountEntity.getKinship());
        account.setPhonebookName(accountEntity.getPhonebookName());
        account.setStatus(accountEntity.getStatus());
        account.setSimilarity(accountEntity.getSimilarity());
        save(account);
    }

    default List<String> getAllPrefixes(){
         List<AccountEntity> accounts = findAll();
            List<String> prefixes = null;
            for(AccountEntity account : accounts){
                prefixes.add(account.getPrefix());
            }
            return prefixes;
    }

    void addInstanceID(UUID userid, String playerid);

    String getKinshipText(Kinship kinship, Lang lang);

    String getNotificationTextByKinship(Kinship kinship, Lang lang);

    String getNotificationText(Lang lang);

    void getAccountMatches(UUID accountid);

    void getDemisesByUserContacts(UUID accountid);

    void updatePhoneBookSync(UUID accountid, List<ContactEntity> hashed, Integer offset);

    void deleteContactsOverIdx(UUID accountid, Integer total);

    List<UUID> findAllByCitY_CityId(UUID accountid);
}
