package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entities.DemiseEntity;
import it.ripapp.ripapp.utilities.SearchSorting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface DemiseRepository extends JpaRepository<it.ripapp.ripapp.entityUpdate.DemiseEntity, UUID> {
    List<DemiseEntity> getDemisesByCitiesWithSorting(List<UUID> cities, SearchSorting sorting, int offset, int searchSize, UUID accountid);

    List<DemiseEntity> findAllBySurnameContains(String surname);
}
