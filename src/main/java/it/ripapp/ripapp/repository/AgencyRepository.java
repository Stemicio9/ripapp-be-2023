package it.ripapp.ripapp.repository;


import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgencyRepository extends JpaRepository<AgencyEntity, Long> {

    Optional<AgencyEntity> findByEmail(String email);

    AgencyEntity findAllByAgencyid(Long agencyid);

    List<AgencyEntity> findAllByNameContains(String query);

}
