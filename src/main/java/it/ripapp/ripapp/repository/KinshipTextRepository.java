package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.EntityUpdate.KinshipText;
import it.ripapp.ripapp.EntityUpdate.compositeKeys.KinshipTextKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KinshipTextRepository extends JpaRepository<KinshipText, KinshipTextKey> {
}
