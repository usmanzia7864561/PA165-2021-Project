package muni.pa165.entity;

import muni.pa165.enums.UserType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Min(6)
    @Max(32)
    private String password;

    @Column
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private UserType type;



    public User(Long id, @NotNull String name, String email, @Min(6) @Max(32) String password, UserType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;

        this.createdAt = LocalDateTime.now();
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
