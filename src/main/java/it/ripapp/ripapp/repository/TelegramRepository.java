package it.ripapp.ripapp.repository;


import it.ripapp.ripapp.entityUpdate.Telegram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TelegramRepository extends JpaRepository<Telegram, UUID> {
}
