package muni.pa165.api.dto;


import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CourtDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String location;

    private LocalDateTime createdAt = LocalDateTime.now();
    private String type;

    private Boolean isAvailable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getLocation() {
        return location;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CourtDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", createdAt=" + createdAt +
                ", type='" + type + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
