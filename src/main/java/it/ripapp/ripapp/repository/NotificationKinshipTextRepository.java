package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.jooqgen.tables.NotificationKinshipText;
import it.ripapp.ripapp.jooqgen.tables.compositeKeys.NotificationKinshipTextKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationKinshipTextRepository extends JpaRepository<NotificationKinshipText, NotificationKinshipTextKey> {
}
