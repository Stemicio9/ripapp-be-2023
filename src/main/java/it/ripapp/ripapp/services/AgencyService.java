package it.ripapp.ripapp.services;


import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import it.ripapp.ripapp.entityUpdate.ProductEntity;
import it.ripapp.ripapp.repository.AccountRepository;
import it.ripapp.ripapp.repository.AgencyRepository;
import it.ripapp.ripapp.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgencyService extends AbstractService{

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private ProductEntityRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;


    public List<AgencyEntity> searchAgency(Long userId, String query){
        // TODO here understand if is needed userId or not, I think it should remain as it is
        return executeAction(() -> agencyRepository.findAllByNameContains(query));
    }

    public List<AccountEntity> searchAccount(Long userId, String query){
        // TODO here understand how to search account by agency or viceversa, should work like this
        return executeAction(() -> accountRepository.findAllByAgency_NameContainsOrAgency_EmailContains(query, query));
    }

    public List<ProductEntity> getAgencyProducts(Long userId, int offset){
        AccountEntity entity = accountRepository.getById(userId);
        AgencyEntity agency = entity.getAgency();
        if(agency == null){
            throw new RuntimeException("User is not an agency operator");
        }
        return executeAction(() -> agency.getProducts().subList(offset, offset + 10));
    }

    public AgencyEntity insertProduct(Long userId, ProductEntity productEntity){
        // TODO here understand how to search account by agency or viceversa
        AccountEntity entity = accountRepository.getById(userId);
        AgencyEntity agencyEntity = entity.getAgency();
        if(agencyEntity == null){
            throw new RuntimeException("User is not an agency operator");
        }
        agencyEntity.getProducts().add(productEntity);
        agencyRepository.save(agencyEntity);
        return agencyEntity;
    }

    public AgencyEntity deleteProduct(Long userId, Long productId){
        // TODO here understand how to search account by agency or viceversa
        AccountEntity entity = accountRepository.getById(userId);
        AgencyEntity agencyEntity = entity.getAgency();
        if(agencyEntity == null){
            throw new RuntimeException("User is not an agency operator");
        }
        agencyEntity.getProducts().removeIf(productEntity -> productEntity.getProductId().equals(productId));
        agencyRepository.save(agencyEntity);
        return agencyEntity;
    }

    public AgencyEntity updateProduct(Long userId, Long productId, ProductEntity productEntity){
        // TODO here understand how to search account by agency or viceversa
        AccountEntity entity = accountRepository.getById(userId);
        AgencyEntity agencyEntity = entity.getAgency();
        if(agencyEntity == null){
            throw new RuntimeException("User is not an agency operator");
        }
        agencyEntity.getProducts().removeIf(productEntity1 -> productEntity1.getProductId().equals(productId));
        agencyEntity.getProducts().add(productEntity);
        agencyRepository.save(agencyEntity);
        return agencyEntity;
    }

    public AgencyEntity saveAgencyEntity(AgencyEntity agency){
        return agencyRepository.save(agency);
    }


    public List<ProductEntity> getAvailableProducts(Long userId) {
        AccountEntity entity = accountRepository.getById(userId);
        AgencyEntity agencyEntity = entity.getAgency();
        if(agencyEntity == null){
            throw new RuntimeException("User is not an agency operator");
        }
        return productRepository.findAll();
    }
}
