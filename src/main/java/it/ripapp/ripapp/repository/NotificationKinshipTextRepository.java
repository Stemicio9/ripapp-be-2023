package it.ripapp.ripapp.repository;


import it.ripapp.ripapp.EntityUpdate.NotificationKinshipText;
import it.ripapp.ripapp.EntityUpdate.compositeKeys.NotificationKinshipTextKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationKinshipTextRepository extends JpaRepository<NotificationKinshipText, NotificationKinshipTextKey> {
}
