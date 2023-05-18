package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.EntityUpdate.NotificationText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTextRepository extends JpaRepository<NotificationText, Long > {
}
