package it.ripapp.ripapp.repository;


import it.ripapp.ripapp.entityUpdate.DemiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DemiseEntityRepository extends JpaRepository<DemiseEntity, UUID> {


}
