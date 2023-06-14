package it.ripapp.ripapp.services;


import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import it.ripapp.ripapp.entityUpdate.DemiseEntity;
import it.ripapp.ripapp.repository.AccountRepository;
import it.ripapp.ripapp.repository.DemiseEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DemiseService extends AbstractService{

    @Autowired
    private DemiseEntityRepository demiseEntityRepository;
    @Autowired
    private AccountRepository accountRepository;


    public List<DemiseEntity> userDemisesAutocomplete(Long accountID, String query){
       // TODO
       // Here understand how to get the user's demises
       // Below an example of how to implement it, for now just mocked an empty linkedlist
       // return executeAction(() -> demiseEntityRepository.findByNameContainingIgnoreCase(query));
        return new LinkedList<DemiseEntity>();
    }

    public List<DemiseEntity> getAgencyDemises(Long accountID, Integer offset){
        // TODO
        // Here understand how to get the agency's demises as above

        AccountEntity agencyOperator = accountRepository.findById(accountID).get();
        List<DemiseEntity> agencyDemises = new ArrayList<>();
        if (agencyOperator != null)
            agencyDemises = agencyOperator.getAgency().getDemises();
        return agencyDemises;
    }

    public List<DemiseEntity> insertDemise(Long accountID, DemiseEntity demise){
       executeAction(() -> demiseEntityRepository.save(demise));
       return getAgencyDemises(accountID, 0);
    }


    //TODO eliminare questo metodo, va usato insertDemise al suo posto
    public DemiseEntity insertDemiseForTesting(DemiseEntity demise){
       return executeAction(() -> demiseEntityRepository.save(demise));
    }

    public List<DemiseEntity> deleteDemiseByID(Long accountID, Long demiseID){
        demiseEntityRepository.deleteById(demiseID);
        return getAgencyDemises(accountID, 0);
    }

    /*public void deleteDemiseByID(Long demiseID){
        demiseEntityRepository.deleteById(demiseID);
    }*/

    public List<DemiseEntity> updateDemise(Long accountID, Long demiseID, DemiseEntity demise){
        Optional<DemiseEntity> demiseEntity = demiseEntityRepository.findById(demiseID);
        if(!demiseEntity.isPresent()){
            throw new RuntimeException("Demise not found");
        }
        demise.setDemiseid(demiseID);
        executeAction(() -> demiseEntityRepository.save(demise));
        return getAgencyDemises(accountID, 0);
    }


    public List<DemiseEntity> getAgencyDemisesIgnorante() {
        System.out.println("ci passo?");
        return demiseEntityRepository.findAll();
    }
}
