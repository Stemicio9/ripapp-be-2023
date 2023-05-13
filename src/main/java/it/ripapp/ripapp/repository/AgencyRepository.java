package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

public interface AgencyRepository extends JpaRepository<it.ripapp.ripapp.entityUpdate.AgencyEntity, UUID> {

    List<AgencyEntity> findAllByEmail(String email);

    AgencyEntity findAllByAgencyid(UUID agencyid);

    List<AgencyEntity> findAllByNameContains(String query);
}
