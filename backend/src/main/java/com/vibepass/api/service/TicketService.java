package com.vibepass.api.service;

import com.vibepass.api.dto.TicketResponse;
import com.vibepass.api.model.Ticket;
import com.vibepass.api.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {
    private final TicketRepository tickets;

    public TicketService(TicketRepository tickets) { this.tickets = tickets; }

    @Transactional
    public TicketResponse verify(String code) {
        Ticket ticket = tickets.findByCode(code.trim().toUpperCase())
            .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        ticket.checkIn();
        return TicketResponse.from(ticket);
    }
}