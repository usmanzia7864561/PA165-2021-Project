package muni.pa165.api.facade;

import muni.pa165.api.dto.UserAuthenticateDTO;
import muni.pa165.api.dto.UserDTO;

import java.util.Collection;

public interface UserFacade {
    UserDTO findUserById(Long userId);

    UserDTO findUserByEmail(String email);

    /**
     * Register the given user with the given unencrypted password.
     */
    void registerUser(UserDTO u, String unencryptedPassword);

    /**
     * Get all registered users
     */
    Collection<UserDTO> getAllUsers();

    /**
     * Try to authenticate a user. Return true only if the hashed password matches the records.
     */
    boolean authenticate(UserAuthenticateDTO u);

    /**
     * Check if the given user is admin.
     */
    boolean isManager(UserDTO u);
}
