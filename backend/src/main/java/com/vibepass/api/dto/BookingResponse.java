package com.vibepass.api.dto;

import com.vibepass.api.model.Booking;
import java.math.BigDecimal;
import java.util.List;

public record BookingResponse(Long id, String event, String attendeeName, String email,
                              int quantity, BigDecimal totalAmount, List<TicketResponse> tickets) {
    public static BookingResponse from(Booking booking, List<TicketResponse> tickets) {
        return new BookingResponse(booking.getId(), booking.getEvent().getTitle(), booking.getAttendeeName(),
            booking.getEmail(), booking.getQuantity(), booking.getTotalAmount(), tickets);
    }
}