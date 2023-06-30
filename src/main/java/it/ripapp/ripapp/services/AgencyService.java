package it.ripapp.ripapp.services;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import it.ripapp.ripapp.dto.AccountSearchEntity;
import it.ripapp.ripapp.dto.ProductOffered;
import it.ripapp.ripapp.entityUpdate.*;
import it.ripapp.ripapp.repository.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerTypePredicate;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AgencyService extends AbstractService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private ProductEntityRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CityEntityRepository cityEntityepository;

    public List<AgencyEntity> searchAgency(Long userId, String query) {
        // TODO here understand if is needed userId or not, I think it should remain as it is
        return executeAction(() -> agencyRepository.findAllByNameContains(query));
    }

    public List<AccountEntity> searchAccount(Long userId, String query) {
        // TODO here understand how to search account by agency or viceversa, should work like this
        return executeAction(() -> accountRepository.findAllByAgency_NameContainsOrAgency_EmailContains(query, query));
    }

    public List<ProductEntity> getAgencyProducts(Long userId, int offset) {
        AccountEntity entity = accountRepository.getById(userId);
        System.out.println("entita: " + entity);
        AgencyEntity agency = entity.getAgency();
        System.out.println("agenzia: " + agency);

        if (agency == null) {
            throw new RuntimeException("User is not an agency operator");
        }
        int indexOfLastElement = (agency.getProducts().size() < (offset + 10)) ? agency.getProducts().size() : (offset + 10);
        return executeAction(() -> agency.getProducts().subList(offset, indexOfLastElement));
    }

    public AgencyEntity deleteAgency(Long idAgency) throws FirebaseAuthException {
        Optional<AgencyEntity> agency = agencyRepository.findById(idAgency);
        if (agency.isEmpty()) {
            throw new RuntimeException("Agency not found");
        }

        List<AccountEntity> accountEntityList = accountRepository.findAll();
        List<String> usersToBeDeleted = new ArrayList<>();

        //delete accounts from sql db
        for (AccountEntity a : accountEntityList) {
            AgencyEntity accountAgency = a.getAgency();
            if (accountAgency != null) {
                if (accountAgency.getAgencyid().equals(idAgency)) {
                    accountRepository.deleteById(a.getAccountid());
                    usersToBeDeleted.add(a.getIdtoken());
                }
            }
        }

        //delete agency from sql db
        agencyRepository.deleteById(idAgency);
        usersToBeDeleted = usersToBeDeleted.stream().filter(Objects::nonNull).collect(Collectors.toList());
        FirebaseAuth fa = FirebaseAuth.getInstance();
        //delete accounts from firebase
        for (String s : usersToBeDeleted) {
            fa.deleteUser(s);
        }

        return agency.get();
    }

    public void setAgencyProducts(Long userId, List<ProductOffered> productsOffered) {
        AccountEntity entity = accountRepository.getById(userId);
        AgencyEntity agency = entity.getAgency();
        List<ProductEntity> agencyProducts = agency.getProducts();
        List<ProductEntity> toRemove = new ArrayList<>();
        for (ProductOffered productOffered : productsOffered) {
            System.out.println("prodotto offerto:" + productOffered);
            if ((!agencyProducts.contains(productOffered.getProduct())) && productOffered.getOffered())
                agencyProducts.add(productOffered.getProduct());
            else if (agencyProducts.contains(productOffered.getProduct()) && !productOffered.getOffered())
                toRemove.add(productOffered.getProduct());

        }
        for (ProductEntity localProduct : agencyProducts) { //locali
            boolean contained = false;
            for (ProductOffered remoteProduct : productsOffered) //aggiornati
                if (localProduct.equals(remoteProduct) && (!remoteProduct.getOffered())) {
                    contained = true;
                    continue;
                }
            if (contained)
                toRemove.add(localProduct);
        }
        for (ProductEntity product : toRemove) agencyProducts.remove(product);
        agency.setProducts(agencyProducts);
        System.out.println("agenzia che sto salvando" + agency);
        agencyRepository.save(agency);
        System.out.println("prodotti aggiornati: " + agencyProducts);
    }

    public AgencyEntity insertProduct(Long userId, ProductEntity productEntity) {
        // TODO here understand how to search account by agency or viceversa
        AccountEntity entity = accountRepository.getById(userId);
        AgencyEntity agencyEntity = entity.getAgency();
        if (agencyEntity == null) {
            throw new RuntimeException("User is not an agency operator");
        }
        agencyEntity.getProducts().add(productEntity);
        agencyRepository.save(agencyEntity);
        return agencyEntity;
    }

    public AgencyEntity deleteProduct(Long userId, Long productId) {
        // TODO here understand how to search account by agency or viceversa
        AccountEntity entity = accountRepository.getById(userId);
        AgencyEntity agencyEntity = entity.getAgency();
        if (agencyEntity == null) {
            throw new RuntimeException("User is not an agency operator");
        }
        agencyEntity.getProducts().removeIf(productEntity -> productEntity.getProductId().equals(productId));
        agencyRepository.save(agencyEntity);
        return agencyEntity;
    }

    public AgencyEntity updateProduct(Long userId, Long productId, ProductEntity productEntity) {
        // TODO here understand how to search account by agency or viceversa
        AccountEntity entity = accountRepository.getById(userId);
        AgencyEntity agencyEntity = entity.getAgency();
        if (agencyEntity == null) {
            throw new RuntimeException("User is not an agency operator");
        }
        agencyEntity.getProducts().removeIf(productEntity1 -> productEntity1.getProductId().equals(productId));
        agencyEntity.getProducts().add(productEntity);
        agencyRepository.save(agencyEntity);
        return agencyEntity;
    }

    public AgencyEntity saveAgencyEntity(AgencyEntity agency) throws SQLIntegrityConstraintViolationException {
        AgencyEntity saved = null;
        try{
            saved = agencyRepository.save(agency);
        }
        catch (Exception e){
            System.out.println("classe causa " + e.getCause().getClass());
            if (e.getCause() instanceof ConstraintViolationException){
                System.out.println("causa messaggio  = " +e.getCause().getCause().getMessage());
                if (e.getCause().getCause().getMessage().startsWith("Duplicate entry"))
                throw new SQLIntegrityConstraintViolationException("indirizzo email già in uso");
            }
        }
        return saved;
    }


    public List<ProductEntity> getAvailableProducts(Long userId) {
        AccountEntity entity = accountRepository.getById(userId);
        AgencyEntity agencyEntity = entity.getAgency();
        if (agencyEntity == null) {
            throw new RuntimeException("User is not an agency operator");
        }
        return productRepository.findAll();
    }

    public List<CityEntity> getCitiesStartingWith(String startsWith){
        return cityEntityepository.findByNameStartsWith(startsWith);
    }


    public Page<AgencyEntity> findAllAgenciesPaged(AccountSearchEntity agencySearch) {
        Pageable page = PageRequest.of(agencySearch.getPageNumber(), agencySearch.getPageElements(), Sort.by("agencyid"));
        return agencyRepository.findAll(page);
    }
}
