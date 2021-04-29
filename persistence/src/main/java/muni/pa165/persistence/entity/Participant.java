package muni.pa165.persistence.entity;

import javax.persistence.*;

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Event event;

    public Participant(){

    }

    public Participant(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setEvent(Event event){
        this.event = event;
    }

    @PreRemove
    public void removeParticipantFromEvent(){
        this.event.removeParticipant(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;

        Participant that = (Participant) o;

        if (!name.equals(that.name)) return false;
        return event.equals(that.event);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + event.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", event=" + event.getName() +
                ", event manager=" + event.getUser() +
                '}';
    }
}
