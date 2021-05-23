package muni.pa165.api.dto;

import muni.pa165.persistence.enums.UserType;

import javax.validation.constraints.NotBlank;

public class UserUpdateDTO {
    @NotBlank
    private String name;

    @NotBlank
    private UserType type;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(String name, UserType type) {
        this.name = name;
        this.type = type;
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
}
