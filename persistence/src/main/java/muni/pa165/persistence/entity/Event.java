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
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Court court;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getEventDate() {
        return eventDate.toString();
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime);
    }

    public String getStartTime() {
        return this.startTime.toString();
    }

    public void setEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime);
    }

    public String getEndTime() {
        return this.endTime.toString();
    }

    public void setEventDate(String eventDate) {
        System.out.println("eventdate"+eventDate);
        this.eventDate = LocalDate.parse(eventDate);
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public void addParticipant(Participant participant){
        participant.setEvent(this);
        this.participants.add(participant);
    }

    public Set<Participant> getParticipants(){
        return this.participants;
    }

    public void removeParticipant(Participant participant){
        this.participants.remove(participant);
    }

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

