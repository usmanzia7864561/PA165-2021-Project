package muni.pa165.persistence.entity;

import muni.pa165.persistence.enums.UserType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

/**
 * User entity
 *
 * @author Muhammad Abdullah
 */

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToMany(mappedBy = "user")
    private Set<Event> events;

    public User(){ }

    /**
     * @param name
     * @param email
     * @param password
     * @param type
     */
    public User(String name, String email, String password, UserType type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.type = type;
    }

    /**
     * @param name
     * @param email
     * @param password
     * @param type
     * @param events
     */
    public User(@NotNull String name, String email, String password, UserType type,Set<Event> events) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.events = events;

        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) { this.password = password; }


    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public UserType getType() {
        return this.type;
    }

    public Set<Event> getEvents() {
        return Collections.unmodifiableSet(this.events);
    }

    public void addEvent(Event event) {
        this.events.add(event);
        event.setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        if (!email.equals(user.email)) return false;
        return type == user.type;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
