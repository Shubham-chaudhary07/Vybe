package com.vibepass.api.controller;

import com.vibepass.api.dto.CreateBookingRequest;
import com.vibepass.api.dto.CreateEventRequest;
import com.vibepass.api.dto.BookingResponse;
import com.vibepass.api.model.Event;
import com.vibepass.api.service.BookingService;
import com.vibepass.api.service.EventService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;
    private final BookingService bookingService;

    public EventController(EventService eventService, BookingService bookingService) {
        this.eventService = eventService;
        this.bookingService = bookingService;
    }

    @GetMapping public List<Event> list() { return eventService.list(); }
    @GetMapping("/{id}") public Event get(@PathVariable long id) { return eventService.get(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Event create(@Valid @RequestBody CreateEventRequest request) { return eventService.create(request); }
    @PostMapping("/{id}/bookings") @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse book(@PathVariable long id, @Valid @RequestBody CreateBookingRequest request) {
        return bookingService.create(id, request);
    }
}