package muni.pa165.rest.models;

import muni.pa165.api.dto.UserResponseDTO;

public class JWTResponse {
    String token;
    UserResponseDTO user;

    public JWTResponse(String token, UserResponseDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}
