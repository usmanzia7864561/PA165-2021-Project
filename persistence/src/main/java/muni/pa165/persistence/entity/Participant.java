package muni.pa165.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Event> events = new HashSet<>();

    public Participant() { }

    public Participant(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

    public void removeEvent(Event event) { this.events.remove(event); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;

        Participant that = (Participant) o;

        if (!name.equals(that.name)) return false;
        return events.equals(that.events);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + events.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
