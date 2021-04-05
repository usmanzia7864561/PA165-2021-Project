package muni.pa165.entity;
import muni.pa165.enums.EventType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Min(3)
    @Max(120)
    private String name;

    @Column
    @Max(512)
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

    public Event(@NotNull @Min(3) @Max(120) String name, @Max(512) String description, LocalTime startTime, LocalTime endTime, LocalDate eventDate, EventType eventType, User createdBy) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.createdBy = createdBy;
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
}

