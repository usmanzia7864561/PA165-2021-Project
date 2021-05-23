package muni.pa165.api.dto;

import muni.pa165.persistence.enums.UserType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Muhammad Abdullah
 */
public class UserDTO {
    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @Size(min = 6,max = 32)
    private String password;

    @NotBlank
    private UserType type;

    public UserDTO() { }

    public UserDTO(Long id,String name, String email, String password, UserType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public UserDTO(String name, String email, String password, UserType type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDTO other = (UserDTO) obj;
        if (email == null) return other.email == null;
        else return email.equals(other.email);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", passwordHash='" + password + '\'' +
                ", email='" + email + '\'' +
                ", givenName='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
