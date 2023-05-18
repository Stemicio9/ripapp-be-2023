package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.EntityUpdate.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, Long> {

}
