package com.vibepass.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateBookingRequest(
    @NotBlank String attendeeName,
    @NotBlank @Email String email,
    @Min(1) int quantity
) {}