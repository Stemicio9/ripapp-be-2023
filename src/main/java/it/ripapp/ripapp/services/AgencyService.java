package it.ripapp.ripapp.services;

import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import it.ripapp.ripapp.repository.AccountRepository;
import it.ripapp.ripapp.repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgencyService extends AbstractService{

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private AccountRepository accountRepository;


    public List<AgencyEntity> searchAgency(UUID userId, String query){
        // TODO here understand if is needed userId or not
        return executeAction(() -> agencyRepository.findAllByNameContains(query));
    }

    public AccountEntity searchAccount(UUID userId, String query){
        // TODO here understand how to search account by agency or viceversa
        return accountRepository.getById(userId);
    }


}
