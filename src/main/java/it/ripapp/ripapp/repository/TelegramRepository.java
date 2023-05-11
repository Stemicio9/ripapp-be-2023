package it.ripapp.ripapp.repository;

import it.ripapp.ripapp.jooqgen.tables.Telegram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TelegramRepository extends JpaRepository<Telegram, UUID> {
}
