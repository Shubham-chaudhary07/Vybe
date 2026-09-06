package com.vibepass.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String title;
    @NotBlank private String organizer;
    @NotBlank private String date;
    @NotBlank private String dateShort;
    @NotBlank private String time;
    @NotBlank private String location;
    @NotNull @DecimalMin("0.0") private BigDecimal price;
    @Min(0) private int spots;
    @NotBlank private String category;
    @NotBlank private String description;

    protected Event() {}

    public Event(String title, String organizer, String date, String dateShort, String time,
                 String location, BigDecimal price, int spots, String category, String description) {
        this.title = title;
        this.organizer = organizer;
        this.date = date;
        this.dateShort = dateShort;
        this.time = time;
        this.location = location;
        this.price = price;
        this.spots = spots;
        this.category = category;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getOrganizer() { return organizer; }
    public String getDate() { return date; }
    public String getDateShort() { return dateShort; }
    public String getTime() { return time; }
    public String getLocation() { return location; }
    public BigDecimal getPrice() { return price; }
    public int getSpots() { return spots; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public void reserve(int quantity) { this.spots -= quantity; }
}