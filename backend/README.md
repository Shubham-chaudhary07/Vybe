# VibePass API (Java backend)

Spring Boot companion backend for the VibePass campus ticketing frontend. It serves
event discovery, booking, ticket verification, and organizer endpoints with an
embedded H2 database, so it runs anywhere with zero setup.

## Prerequisites

- Java 21+
- Maven 3.9+ (or use `./mvnw` if you generate the wrapper)

## Run

```bash
cd backend
mvn spring-boot:run
```

The API starts on **http://localhost:8081**. The H2 console is at
`http://localhost:8081/h2-console` (JDBC URL `jdbc:h2:mem:vibepass`, user `sa`, no password).

On startup the database is seeded with the same four North Campus demo events and
attendee tickets you see in the frontend.

## Test

```bash
mvn test
```

## Endpoints

| Method | Path | Description |
| --- | --- | --- |
| GET | `/api/health` | Health check |
| GET | `/api/events` | List all events |
| GET | `/api/events/{id}` | Get one event |
| POST | `/api/events` | Create an event |
| POST | `/api/events/{id}/bookings` | Book tickets (generates `VP-XXXX` codes) |
| POST | `/api/tickets/{code}/verify` | Verify + check in a ticket (idempotent) |
| GET | `/api/organizer/summary` | Live events / tickets sold / checked in |
| GET | `/api/organizer/attendees` | Download attendee CSV |

### Examples

```bash
# List events
curl http://localhost:8081/api/events

# Book 2 tickets for event 1
curl -X POST http://localhost:8081/api/events/1/bookings \
  -H "Content-Type: application/json" \
  -d '{"attendeeName":"Aarav Mehta","email":"aarav@campus.edu","quantity":2}'

# Verify a ticket code returned by the booking
curl -X POST http://localhost:8081/api/tickets/VP-84K2/verify

# Export attendees
curl -OJ http://localhost:8081/api/organizer/attendees
```

Errors return a JSON body: `{ "timestamp", "status", "error", "message" }` with
404 for missing resources, 409 for sold-out bookings, and 400 for invalid input.

## Connecting the frontend

The React frontend currently keeps demo state in the browser. To go live, point its
fetches at `http://localhost:8081/api/...` — CORS is already open for
`localhost:8080`, `5173`, and `3000`. Replace the in-memory event list with
`GET /api/events`, the demo checkout with `POST /api/events/{id}/bookings`, the
entry desk with `POST /api/tickets/{code}/verify`, and the CSV export with
`GET /api/organizer/attendees`.

## Structure

```
backend/
  pom.xml
  src/main/java/com/vibepass/api/
    VibePassApplication.java
    config/      CORS + demo data seeder
    controller/  REST controllers (events, tickets, organizer, health)
    dto/         Request/response records
    exception/   Global error handler
    model/       JPA entities (Event, Booking, Ticket, Attendee)
    repository/  Spring Data repositories
    service/     Booking, event, and ticket logic
  src/main/resources/application.properties
  src/test/java/com/vibepass/api/VibePassApiTests.java
```
