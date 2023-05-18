package it.ripapp.ripapp.repository;


import it.ripapp.ripapp.EntityUpdate.DemiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DemiseEntityRepository extends JpaRepository<DemiseEntity, Long> {


}
