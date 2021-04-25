package muni.pa165.entity;
import muni.pa165.enums.EventType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


/**
 * Event entity
 *
 * @author Usman Zia
 */
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column
    private String description ;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    @Enumerated
    private EventType eventType;

    @OneToOne
    private User createdBy;

    @ManyToOne
    private Court court;

    public Event(@NotNull String name, LocalTime startTime, LocalTime endTime, LocalDate eventDate, EventType eventType, User createdBy) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.createdBy = createdBy;
    }

    public Event(@NotNull String name, String description, LocalTime startTime, LocalTime endTime, LocalDate eventDate, EventType eventType, User createdBy) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return name.equals(event.name) && startTime.equals(event.startTime) && eventDate.equals(event.eventDate) && createdBy.equals(event.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startTime, eventDate, createdBy);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", eventDate=" + eventDate +
                ", eventType=" + eventType +
                ", createdBy=" + createdBy +
                '}';
    }
}

