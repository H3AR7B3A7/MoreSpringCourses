package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface TimeSlotsJpaRepository extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findByStartTimeBefore(LocalTime time);
}