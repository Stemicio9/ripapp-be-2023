package it.ripapp.ripapp.services;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.SessionCookieOptions;
import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import it.ripapp.ripapp.entityUpdate.PhoneBookSyncEntity;
import it.ripapp.ripapp.exceptions.BadRequestException;
import it.ripapp.ripapp.exceptions.ForbiddenException;
import it.ripapp.ripapp.exceptions.ResponseException;
import it.ripapp.ripapp.repository.AccountRepository;
import it.ripapp.ripapp.repository.AgencyRepository;
import it.ripapp.ripapp.utilities.FirebaseAuthCookieData;
import it.ripapp.ripapp.utilities.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService extends AbstractService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AgencyRepository agencyRepository;


    public FirebaseAuthCookieData getUserbaseUUIDByFirebaseToken(String token) throws ResponseException, FirebaseAuthException {


        if (token == null)
            throw new BadRequestException("token not provided");

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        String cookie = FirebaseAuth.getInstance().createSessionCookie(token, SessionCookieOptions.builder().setExpiresIn(TimeUnit.DAYS.toMillis(1)).build());

        AccountEntity account = accountRepository.findByEmail(decodedToken.getEmail());

        try {
            if (!account.getEnabled())
                throw new ForbiddenException("Account disabled");
        }catch(Exception e){
            // IF this exception is thrown, it means that the account is enabled (by default enabled is null, that means enabled)
        }

        return new FirebaseAuthCookieData(account.getIdtoken(), cookie);
    }

    public AccountEntity accountFromToken(String token) throws Exception{
        if (token == null)
            throw new BadRequestException("token not provided");

       // FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifySessionCookie(token);
        AccountEntity account = accountRepository.findByEmail(decodedToken.getEmail());
        return account;
    }
    public List<AccountEntity> getAllUser(){
        return accountRepository.findAll();
    }



    public AccountEntity saveUser(AccountEntity user){
        user.setAccountid(null);
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

    public AccountEntity getAccountByFirebaseID(String firebaseId){
        return executeAction(() -> accountRepository.findByIdtoken(firebaseId));
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
    public AccountEntity deleteUser(Long idUser){
        Optional<AccountEntity> account = accountRepository.findById(idUser);
        if(account.isEmpty()){
            throw new RuntimeException("User not found");
        }
        accountRepository.deleteById(idUser);
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

    private AccountEntity saveUserFlow(AccountEntity accountEntity ){
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
