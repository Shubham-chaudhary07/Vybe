package com.vibepass.api.repository;

import com.vibepass.api.model.Ticket;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByCode(String code);
    long countByStatus(com.vibepass.api.model.TicketStatus status);
}