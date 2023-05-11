package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.entityUpdate.compositeKeys.KinshipTextKey;
import it.ripapp.ripapp.jooqgen.tables.KinshipText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KinshipTextRepository extends JpaRepository<KinshipText, KinshipTextKey> {
}
