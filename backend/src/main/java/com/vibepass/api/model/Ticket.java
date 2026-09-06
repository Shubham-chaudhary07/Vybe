package com.vibepass.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
    private String code;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private Instant checkedInAt;

    protected Ticket() {}

    public Ticket(Booking booking, String code) {
        this.booking = booking;
        this.code = code;
        this.status = TicketStatus.READY;
    }

    public Long getId() { return id; }
    public Booking getBooking() { return booking; }
    public String getCode() { return code; }
    public TicketStatus getStatus() { return status; }
    public Instant getCheckedInAt() { return checkedInAt; }
    public void checkIn() { this.status = TicketStatus.CHECKED_IN; this.checkedInAt = Instant.now(); }
}