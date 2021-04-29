package muni.pa165.services;

import muni.pa165.persistence.entity.User;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;


/**
 * Interface for User service
 * @author Muhammad Abdullah
 */
@Service
public interface UserService {
    /**
     * Register the given user with the given unencrypted password.
     * @return
     */
    User registerUser(User u) throws ValidationException;

    /**
     * Get all registered users
     */
    List<User> getAllUsers();

    /**
     * Try to authenticate a user. Return true only if the hashed password matches the records.
     */
    boolean authenticate(User u, String password);

    /**
     * Check if the given user is admin.
     */
    boolean isManager(User u);

    Optional<User> findUserById(Long userId);

    Optional<User> findUserByEmail(String email);
}
