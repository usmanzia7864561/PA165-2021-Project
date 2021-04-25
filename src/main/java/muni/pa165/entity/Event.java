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

    @ManyToOne
    private User user;

    @ManyToOne
    private Court court;

    public Event() {
    }

    public Event(@NotNull String name, LocalTime startTime, LocalTime endTime, LocalDate eventDate, EventType eventType) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventDate = eventDate;
        this.eventType = eventType;
    }

    public Event(@NotNull String name, String description, LocalTime startTime, LocalTime endTime, LocalDate eventDate, EventType eventType, User user) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Court getCourt() { return this.court; }
    public void setCourt(Court court) { this.court = court; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return name.equals(event.name) && startTime.equals(event.startTime) && eventDate.equals(event.eventDate) && user.equals(event.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startTime, eventDate, user);
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
                ", createdBy=" + user +
                '}';
    }
}

