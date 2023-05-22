package it.ripapp.ripapp.repository;


import it.ripapp.ripapp.entityUpdate.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactEntityRepository extends JpaRepository<ContactEntity, Long> {
}
