package it.ripapp.ripapp.services;

import it.ripapp.ripapp.EntityUpdate.AccountEntity;
import it.ripapp.ripapp.EntityUpdate.AgencyEntity;
import it.ripapp.ripapp.EntityUpdate.PhoneBookSyncEntity;
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
        return executeAction(() -> saveUserFlow(user));
    }

    public UserStatus getUserStatusByEmail(String email){
        return executeAction(() -> accountRepository.findByEmail(email).getStatus());
    }

    public AccountEntity updateUserByID(Long userId, AccountEntity accountEntity){
        // here is a draft of how to update AccountEntity
        Optional<AccountEntity> account = accountRepository.findById(userId);
        if(account.isEmpty()){
            throw new RuntimeException("User not found");
        }
        accountEntity.setAccountid(userId);
        return executeAction(() -> accountRepository.save(accountEntity));
    }

    public AccountEntity getAccountByID(Long userId){
        return executeAction(() -> accountRepository.findById(userId).orElseThrow());
    }

    public AccountEntity syncPhoneBook(Long userId, Collection<PhoneBookSyncEntity> phoneBookSyncEntity){
        Optional<AccountEntity> account = accountRepository.findById(userId);
        if(account.isEmpty()){
            throw new RuntimeException("User not found");
        }
        // TODO here understand phonebook logic and implement it
        // for now just returning account found by id --- MOCKED
        return account.get();
    }

    public AccountEntity deleteAccount(Long userId){
        Optional<AccountEntity> account = accountRepository.findById(userId);
        if(account.isEmpty()){
            throw new RuntimeException("User not found");
        }
        accountRepository.deleteById(userId);
        return account.get();
    }

    public AccountEntity sendPhoneBook(Long userId, Long agencyId, String file){
        Optional<AccountEntity> account = accountRepository.findById(userId);
        Optional<AgencyEntity> agency = agencyRepository.findById(agencyId);
        if(account.isEmpty() || agency.isEmpty()){
            throw new RuntimeException("User not found");
        }
        // TODO here understand phonebook logic and implement it
        // for now just returning account found by id --- MOCKED
        return account.get();
    }

    public AccountEntity addPlayerID(Long userId, String playerID){
        Optional<AccountEntity> account = accountRepository.findById(userId);
        if(account.isEmpty()){
            throw new RuntimeException("User not found");
        }
        // TODO what the hell is this playerID ???
      //  account.get().setPlayerid(playerID);
        return executeAction(() -> accountRepository.save(account.get()));
    }

    private AccountEntity saveUserFlow(AccountEntity accountEntity){
        // If the user is a customer or an admin, we save it as a normal user
        if(accountEntity.getStatus().equals(UserStatus.active)
                || accountEntity.getStatus().equals(UserStatus.disabled)
                || accountEntity.getStatus().equals(UserStatus.admin)){
            return accountRepository.save(accountEntity);
        }
        // If the user is an agency, we save the agency before
        else if(accountEntity.getStatus().equals(UserStatus.agency)){
            String agencyEmail = accountEntity.getAgency().getEmail();
            Optional<AgencyEntity> agency = agencyRepository.findByEmail(agencyEmail);
            AgencyEntity agencyEntity = null;
            // We can write this section in 1 row, but for readability we split it !!! Do not follow Intellij hint
            if(agency.isEmpty()){
                // here we need to save agency object
                agencyEntity = agencyRepository.save(accountEntity.getAgency());
            } else {
                // here we need to assign the correct agency to user
                agencyEntity = agency.get();
            }
            accountEntity.setAgency(agencyEntity);
            return accountRepository.save(accountEntity);
        }

        else {
            // we should not be here in no case
            throw new RuntimeException("User status not valid");
        }

    }

}
