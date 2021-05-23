package muni.pa165.api.dto;

import muni.pa165.persistence.entity.Event;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ParticipantDTO {
    private Long id;

    @Size(min = 2,max = 125)
    private String name;


    public ParticipantDTO() { }

    public ParticipantDTO(String name,Event event) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
