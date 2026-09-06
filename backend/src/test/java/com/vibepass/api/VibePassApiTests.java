package com.vibepass.api;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class VibePassApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listsSeededEvents() throws Exception {
        mockMvc.perform(get("/api/events"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(4)));
    }

    @Test
    void booksTicketsAndVerifiesThem() throws Exception {
        MvcResult booking = mockMvc.perform(post("/api/events/1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"attendeeName\":\"Test User\",\"email\":\"test@campus.edu\",\"quantity\":2}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.tickets.length()").value(2))
            .andReturn();

        String body = booking.getResponse().getContentAsString();
        String code = body.split("\"code\":\"")[1].split("\"")[0];

        mockMvc.perform(post("/api/tickets/" + code + "/verify"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("CHECKED_IN"));
    }

    @Test
    void rejectsInvalidBooking() throws Exception {
        mockMvc.perform(post("/api/events/1/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"attendeeName\":\"\",\"email\":\"not-an-email\",\"quantity\":0}"))
            .andExpect(status().isBadRequest());
    }
}
