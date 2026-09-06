package com.vibepass.api.controller;

import com.vibepass.api.model.Attendee;
import com.vibepass.api.model.TicketStatus;
import com.vibepass.api.repository.AttendeeRepository;
import com.vibepass.api.repository.EventRepository;
import com.vibepass.api.repository.TicketRepository;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {
    private final EventRepository events;
    private final TicketRepository tickets;
    private final AttendeeRepository attendees;

    public OrganizerController(EventRepository events, TicketRepository tickets, AttendeeRepository attendees) {
        this.events = events; this.tickets = tickets; this.attendees = attendees;
    }

    @GetMapping("/summary")
    public Map<String, Object> summary() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("liveEvents", events.count());
        result.put("ticketsSold", tickets.count());
        result.put("checkedIn", tickets.countByStatus(TicketStatus.CHECKED_IN));
        return result;
    }

    @GetMapping(value = "/attendees", produces = "text/csv")
    public ResponseEntity<String> exportAttendees() {
        List<Attendee> rows = attendees.findAll();
        StringBuilder csv = new StringBuilder("Name,Event,Ticket,Status\n");
        rows.forEach(row -> csv.append(row.getName()).append(',').append(row.getEvent()).append(',')
            .append(row.getCode()).append(',').append(row.getStatus()).append('\n'));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vibepass-attendees.csv")
            .contentType(MediaType.parseMediaType("text/csv")).body(csv.toString());
    }
}