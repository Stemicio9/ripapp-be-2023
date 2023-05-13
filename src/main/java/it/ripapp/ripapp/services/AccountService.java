package it.ripapp.ripapp.services;

import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import it.ripapp.ripapp.entityUpdate.PhoneBookSyncEntity;
import it.ripapp.ripapp.repository.AccountRepository;
import it.ripapp.ripapp.repository.AgencyRepository;
import it.ripapp.ripapp.utilities.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService extends AbstractService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AgencyRepository agencyRepository;


    public AccountEntity saveUser(AccountEntity user){
        // TODO here we need to check if UUID should be generated here or in the database to avoid duplicates
        return executeAction(() -> accountRepository.save(user));
    }

    public UserStatus getUserStatusByEmail(String email){
        return executeAction(() -> accountRepository.findByEmail(email).getStatus());
    }

    public AccountEntity updateUserByID(UUID userId, AccountEntity accountEntity){
        // here is a draft of how to update AccountEntity
        Optional<AccountEntity> account = accountRepository.findById(userId);
        if(account.isEmpty()){
            throw new RuntimeException("User not found");
        }
        accountEntity.setAccountid(userId);
        return executeAction(() -> accountRepository.save(accountEntity));
    }

    public AccountEntity getAccountByID(UUID userId){
        return executeAction(() -> accountRepository.findById(userId).orElseThrow());
    }

    public AccountEntity syncPhoneBook(UUID userId, Collection<PhoneBookSyncEntity> phoneBookSyncEntity){
        Optional<AccountEntity> account = accountRepository.findById(userId);
        if(account.isEmpty()){
            throw new RuntimeException("User not found");
        }
        // TODO here understand phonebook logic and implement it
        // for now just returning account found by id --- MOCKED
        return account.get();
    }

    public AccountEntity deleteAccount(UUID userId){
        Optional<AccountEntity> account = accountRepository.findById(userId);
        if(account.isEmpty()){
            throw new RuntimeException("User not found");
        }
        accountRepository.deleteById(userId);
        return account.get();
    }

    public AccountEntity sendPhoneBook(UUID userId, UUID agencyId, String file){
        Optional<AccountEntity> account = accountRepository.findById(userId);
        Optional<AgencyEntity> agency = agencyRepository.findById(agencyId);
        if(account.isEmpty() || agency.isEmpty()){
            throw new RuntimeException("User not found");
        }
        // TODO here understand phonebook logic and implement it
        // for now just returning account found by id --- MOCKED
        return account.get();
    }

    public AccountEntity addPlayerID(UUID userId, String playerID){
        Optional<AccountEntity> account = accountRepository.findById(userId);
        if(account.isEmpty()){
            throw new RuntimeException("User not found");
        }
        // TODO what the hell is this playerID ???
      //  account.get().setPlayerid(playerID);
        return executeAction(() -> accountRepository.save(account.get()));
    }

}
