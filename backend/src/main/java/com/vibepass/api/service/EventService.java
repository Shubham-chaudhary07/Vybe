package com.vibepass.api.service;

import com.vibepass.api.dto.CreateEventRequest;
import com.vibepass.api.model.Event;
import com.vibepass.api.repository.EventRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository events;

    public EventService(EventRepository events) { this.events = events; }

    public List<Event> list() { return events.findAll(); }
    public Event get(long id) { return events.findById(id).orElseThrow(() -> new IllegalArgumentException("Event not found: " + id)); }
    public Event create(CreateEventRequest request) {
        return events.save(new Event(request.title(), request.organizer(), request.date(), request.dateShort(),
            request.time(), request.location(), request.price(), request.spots(), request.category(), request.description()));
    }
}