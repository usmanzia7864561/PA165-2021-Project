package muni.pa165.rest.config;

import org.springframework.security.core.GrantedAuthority;

public class Roles implements GrantedAuthority{
    public static final String MANAGER = "MANAGER";
    public static final String TENNIS_USER = "TENNIS_USER";

    String authority;

    public Roles(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
