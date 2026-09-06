package com.vibepass.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateEventRequest(
    @NotBlank String title,
    @NotBlank String organizer,
    @NotBlank String date,
    @NotBlank String dateShort,
    @NotBlank String time,
    @NotBlank String location,
    @NotNull @DecimalMin("0.0") BigDecimal price,
    @Min(0) int spots,
    @NotBlank String category,
    @NotBlank String description
) {}