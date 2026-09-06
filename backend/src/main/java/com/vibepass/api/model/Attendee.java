package com.vibepass.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendees")
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String event;
    private String code;
    private String status;

    protected Attendee() {}

    public Attendee(String name, String event, String code, String status) {
        this.name = name;
        this.event = event;
        this.code = code;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEvent() { return event; }
    public String getCode() { return code; }
    public String getStatus() { return status; }
}