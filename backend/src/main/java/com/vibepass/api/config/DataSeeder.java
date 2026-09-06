package com.vibepass.api.config;

import com.vibepass.api.model.Attendee;
import com.vibepass.api.model.Event;
import com.vibepass.api.repository.AttendeeRepository;
import com.vibepass.api.repository.EventRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDemoData(EventRepository events, AttendeeRepository attendees) {
        return args -> {
            if (events.count() > 0) return;
            events.saveAll(List.of(
                new Event("Pulse: Neon Night", "Cultural Society", "Sat, 14 Sep 2026", "14 Sep", "7:00 PM",
                    "Main Auditorium, North Campus", new BigDecimal("299"), 120, "Music",
                    "A high-energy night of live bands, DJ sets, and neon lights. The biggest party of the semester."),
                new Event("TEDx: Ideas Worth Spreading", "Literary Club", "Sun, 22 Sep 2026", "22 Sep", "10:00 AM",
                    "Seminar Hall B", new BigDecimal("149"), 80, "Talks",
                    "Six speakers, six bold ideas. Talks on tech, art, and everything in between."),
                new Event("Hacknight 48", "Coding Club", "Fri, 27 Sep 2026", "27 Sep", "6:00 PM",
                    "Innovation Lab, Block C", new BigDecimal("0"), 60, "Tech",
                    "48 hours of building, pizza, and prizes. Bring your laptop and your wildest idea."),
                new Event("Sunrise Run 5K", "Sports Committee", "Sun, 6 Oct 2026", "6 Oct", "6:00 AM",
                    "Campus Main Gate", new BigDecimal("99"), 200, "Sports",
                    "A 5K run through campus at sunrise. Medals, breakfast, and bragging rights.")
            ));
            attendees.saveAll(List.of(
                new Attendee("Aarav Mehta", "Pulse: Neon Night", "VP-84K2", "Confirmed"),
                new Attendee("Ishita Rao", "TEDx: Ideas Worth Spreading", "VP-91M7", "Confirmed"),
                new Attendee("Kabir Shah", "Hacknight 48", "VP-20P4", "Checked-in"),
                new Attendee("Meera Nair", "Sunrise Run 5K", "VP-62A9", "Confirmed")
            ));
        };
    }
}
