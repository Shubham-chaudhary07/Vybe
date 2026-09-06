package com.vibepass.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    private String attendeeName;
    private String email;
    private int quantity;
    private BigDecimal totalAmount;
    private Instant createdAt;

    protected Booking() {}

    public Booking(Event event, String attendeeName, String email, int quantity, BigDecimal totalAmount) {
        this.event = event;
        this.attendeeName = attendeeName;
        this.email = email;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public Event getEvent() { return event; }
    public String getAttendeeName() { return attendeeName; }
    public String getEmail() { return email; }
    public int getQuantity() { return quantity; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public Instant getCreatedAt() { return createdAt; }
}