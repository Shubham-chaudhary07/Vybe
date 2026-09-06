package com.vibepass.api.controller;

import com.vibepass.api.dto.TicketResponse;
import com.vibepass.api.service.TicketService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService) { this.ticketService = ticketService; }
    @PostMapping("/{code}/verify") public TicketResponse verify(@PathVariable String code) { return ticketService.verify(code); }
}