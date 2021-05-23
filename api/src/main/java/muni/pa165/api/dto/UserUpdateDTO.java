package muni.pa165.api.dto;

import muni.pa165.persistence.enums.UserType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserUpdateDTO {
    @NotBlank
    private String name;

    @NotBlank
    private UserType type;

    @Size(min = 6,max = 32)
    private String password;

    public UserUpdateDTO() {}

    public UserUpdateDTO(String name, UserType type,String password) {
        this.name = name;
        this.type = type;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
