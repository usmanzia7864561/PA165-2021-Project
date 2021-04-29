package muni.pa165.api.dto;

import muni.pa165.persistence.enums.UserType;

import java.time.LocalDateTime;

public class CourtDTO {
    private Long id;
    private LocalDateTime createdAt;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
