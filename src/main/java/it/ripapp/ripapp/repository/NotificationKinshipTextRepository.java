package it.ripapp.ripapp.repository;



import it.ripapp.ripapp.entityUpdate.NotificationKinshipText;
import it.ripapp.ripapp.entityUpdate.compositeKeys.NotificationKinshipTextKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationKinshipTextRepository extends JpaRepository<NotificationKinshipText, NotificationKinshipTextKey> {
}
