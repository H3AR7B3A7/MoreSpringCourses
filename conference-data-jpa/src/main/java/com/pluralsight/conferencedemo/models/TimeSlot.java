package com.pluralsight.conferencedemo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "time_slots")
public class TimeSlot {
    @Id
    @Column(name = "time_slot_id")
    private Long id;
    
    @Column(name = "time_slot_date")
    private LocalDate timeSlotDate;
    
    @Column(name = "start_time")
    private LocalTime startTime;
    
    @Column(name = "end_time")
    private LocalTime endTime;
    
    @Column(name = "is_keynote_time_slot")
    private Boolean isKeyNoteTimeSlot;

    public TimeSlot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTimeSlotDate() {
        return timeSlotDate;
    }

    public void setTimeSlotDate(LocalDate timeSlotDate) {
        this.timeSlotDate = timeSlotDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getKeyNoteTimeSlot() {
        return isKeyNoteTimeSlot;
    }

    public void setKeyNoteTimeSlot(Boolean keyNoteTimeSlot) {
        isKeyNoteTimeSlot = keyNoteTimeSlot;
    }
}