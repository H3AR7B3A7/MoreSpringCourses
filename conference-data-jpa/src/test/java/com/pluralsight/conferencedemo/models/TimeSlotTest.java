package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.TimeSlotsJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TimeSlotTest {
    @Autowired
    private TimeSlotsJpaRepository timeSlotsJpaRepository;

    @Test
    void jpaBeforeTest() {
        List<TimeSlot> timeSlots = timeSlotsJpaRepository.findByStartTimeBefore(LocalTime.of(10, 0));
        assertTrue(timeSlots.size() > 0);
    }
}