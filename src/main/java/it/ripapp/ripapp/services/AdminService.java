package it.ripapp.ripapp.services;

import it.ripapp.ripapp.entityUpdate.ProductEntity;
import it.ripapp.ripapp.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends AbstractService{

    @Autowired
    ProductEntityRepository productEntityRepository;

    public ProductEntity saveProductEntity(ProductEntity productEntity){
        return productEntityRepository.save(productEntity);
    }
}
