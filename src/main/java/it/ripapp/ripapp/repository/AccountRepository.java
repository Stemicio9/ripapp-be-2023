package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.bll.Kinship;
import it.ripapp.ripapp.bll.Lang;
import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.entityUpdate.CityEntity;
import it.ripapp.ripapp.entityUpdate.ContactEntity;
import it.ripapp.ripapp.entityUpdate.DemiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<it.ripapp.ripapp.entityUpdate.AccountEntity, UUID> {



}
