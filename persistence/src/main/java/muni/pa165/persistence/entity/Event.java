package muni.pa165.persistence.entity;
import muni.pa165.persistence.enums.EventType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


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

    @ManyToMany
    @MapsId("event")
    private Set<Participant> participants = new HashSet<>();

    public Event() { }

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

    public void setCourt(Court court) {
        this.court = court;
        court.addEvent(this);
    }

    public void addParticipant(Participant participant){
        participant.addEvent(this);
        this.participants.add(participant);
    }

    public Set<Participant> getParticipants(){
        return this.participants;
    }

    public void removeParticipant(Participant participant){
        this.participants.remove(participant);
        participant.removeEvent(this);
    }
/*
    @PreRemove
    public void removeParticipantsFromEvent(){
        for (Participant participant:this.participants){
            participant.removeEvent(this);
        }
    }
*/
    @PreRemove
    public void removeEventFromCourt(){
        this.court.removeEvent(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return name.equals(event.name) && startTime.equals(event.startTime) && eventDate.equals(event.eventDate);
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

