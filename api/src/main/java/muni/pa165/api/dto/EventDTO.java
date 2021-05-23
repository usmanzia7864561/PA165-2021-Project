package muni.pa165.api.dto;

import muni.pa165.persistence.entity.Participant;
import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.EventType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private String eventDate;
    private EventType eventType;

    public EventDTO() { }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public EventDTO(String name, String description, String startTime, String endTime, String eventDate, EventType type,Set<Participant> participants) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventDate = eventDate;
        this.eventType = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", eventDate=" + eventDate +
                ", eventType=" + eventType +
                '}';
    }
}
