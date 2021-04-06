package muni.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(nullable = false)
    private Boolean isAvailable;

    @OneToMany(mappedBy = "court")
    private Set<Event> events;

    public Court(String name, String location, String type, Boolean isAvailable) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public Court(String name, String location, String type, Boolean isAvailable, Set<Event> events) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.isAvailable = isAvailable;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public Set<Event> getEvents() {
        return events;
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
}
