package it.ripapp.ripapp.services;

import it.ripapp.ripapp.dto.AccountSearchEntity;
import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import it.ripapp.ripapp.entityUpdate.ProductEntity;
import it.ripapp.ripapp.repository.AgencyRepository;
import it.ripapp.ripapp.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService extends AbstractService{

    @Autowired
    ProductEntityRepository productEntityRepository;
    @Autowired
    AgencyRepository agencyRepository;

    public ProductEntity saveProductEntity(ProductEntity productEntity){
        return productEntityRepository.save(productEntity);
    }

    public List<AgencyEntity> findAllAgencies() {
        return agencyRepository.findAll();
    }

    public List<ProductEntity> findAllProducts(){
        return productEntityRepository.findAll();
    }


    public void deleteProduct(Long productId) {productEntityRepository.deleteById(productId);}

    public Page<ProductEntity> findAllProcuctsPaged(AccountSearchEntity productSearch) {
        Pageable page = PageRequest.of(productSearch.getPageNumber(), productSearch.getPageElements(), Sort.by("productId"));
        return productEntityRepository.findAll(page);
    }
}
