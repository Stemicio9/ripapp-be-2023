package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entityUpdate.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductEntityRepository extends JpaRepository<ProductEntity, UUID> {
}
