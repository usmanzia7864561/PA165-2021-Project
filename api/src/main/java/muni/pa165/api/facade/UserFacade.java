package muni.pa165.api.facade;

import muni.pa165.api.dto.*;

import java.util.Collection;

/**
 * UserFacade interfaces for all possible methods for it's implementation
 *
 * @author Muhammad Abdullah
 */
public interface UserFacade {
    /**
     * Find a user by it's id
     * @param userId
     * @return
     */
    UserResponseDTO findUserById(Long userId);

    /**
     * Find a user by it's email
     * @param email
     * @return
     */
    UserResponseDTO findUserByEmail(String email);

    /**
     * Register the given user with the given unencrypted password.
     */
    UserDTO registerUser(UserDTO u);

    /**
     * Get all registered users
     */
    Collection<UserResponseDTO> getAllUsers();

    /**
     * Try to authenticate a user. Return true only if the hashed password matches the records.
     */
    boolean authenticate(UserAuthenticateDTO u);

    /**
     * delete a user from database.
     */
    boolean delete(long id);

    /**
     * Check if the given user is admin.
     */
    boolean isManager(UserDTO u);

    UserUpdateDTO update(long id, UserUpdateDTO userUpdateDTO);
}
