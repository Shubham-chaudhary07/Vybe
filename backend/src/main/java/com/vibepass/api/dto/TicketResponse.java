package com.vibepass.api.dto;

import com.vibepass.api.model.Ticket;
import com.vibepass.api.model.TicketStatus;
import java.time.Instant;

public record TicketResponse(String code, String event, String attendeeName, TicketStatus status, Instant checkedInAt) {
    public static TicketResponse from(Ticket ticket) {
        return new TicketResponse(ticket.getCode(), ticket.getBooking().getEvent().getTitle(),
            ticket.getBooking().getAttendeeName(), ticket.getStatus(), ticket.getCheckedInAt());
    }
}