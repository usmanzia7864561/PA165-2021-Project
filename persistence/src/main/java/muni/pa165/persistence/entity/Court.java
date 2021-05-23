package muni.pa165.persistence.entity;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Court entity
 *
 * @author Muhammad Usman
 */
@Entity
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false , columnDefinition = "boolean default false")
    private Boolean isAvailable;

    @OneToMany(mappedBy = "court",cascade = CascadeType.ALL)
    private Set<Event> events = new HashSet<>();

    @Column
    LocalDateTime createdAt;

    public Court() {
        this.createdAt = LocalDateTime.now();
    }

    public Court(String name, String location, String type, Boolean isAvailable) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.isAvailable = isAvailable;
        this.createdAt = LocalDateTime.now();
    }

    public Court(String name, String location, String type, Boolean isAvailable, Set<Event> events) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.isAvailable = isAvailable;
        this.events = events;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public Boolean isCourtAvailable() {
        return isAvailable;
    }

    public void addEvent(Event event) {
        event.setCourt(this);
        this.events.add(event);
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }

    public String getCreatedAt() {
        return createdAt.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Court)) return false;
        Court court = (Court) o;
        return name.equals(court.name) && location.equals(court.location) && type.equals(court.type);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, location, type);
    }

    @Override
    public String toString() {
        return "Court{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", isAvailable=" + isAvailable +
                ", events=" + events +
                '}';
    }
}
