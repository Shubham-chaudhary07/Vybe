package com.vibepass.api.service;

import com.vibepass.api.dto.BookingResponse;
import com.vibepass.api.dto.CreateBookingRequest;
import com.vibepass.api.dto.TicketResponse;
import com.vibepass.api.model.Booking;
import com.vibepass.api.model.Event;
import com.vibepass.api.model.Ticket;
import com.vibepass.api.repository.BookingRepository;
import com.vibepass.api.repository.EventRepository;
import com.vibepass.api.repository.TicketRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {
    private final EventRepository events;
    private final BookingRepository bookings;
    private final TicketRepository tickets;

    public BookingService(EventRepository events, BookingRepository bookings, TicketRepository tickets) {
        this.events = events;
        this.bookings = bookings;
        this.tickets = tickets;
    }


    @Transactional
    public BookingResponse create(long eventId, CreateBookingRequest request) {

        Event event = events.findById(eventId).
                orElseThrow(() ->
                        new IllegalArgumentException("Event not found hey: " + eventId));

        if (request.quantity() > event.getSpots()) {
            throw new IllegalStateException("Not enough spots available");

        }
        BigDecimal totalAmount=event.getPrice().multiply(BigDecimal.valueOf(request.quantity()));

        event.reserve(request.quantity());



        events.save(event);
        Booking booking = bookings.save(
                new Booking(event, request.attendeeName(), request.email(), request.quantity(),
            event.getPrice().multiply(java.math.BigDecimal.valueOf(request.quantity()))));
        List<Ticket> created = java.util.stream.IntStream.range(0, request.quantity())
            .mapToObj(index -> tickets.save(new Ticket(booking, createCode())))
            .toList();
        return BookingResponse.from(booking, created.stream().map(TicketResponse::from).toList());
    }

    private String createCode() { return "VP-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase(); }
}