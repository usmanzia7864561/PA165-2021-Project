package muni.pa165.services;

import muni.pa165.persistence.dao.UserDao;
import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.UserType;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

/**
 * User service for registering user and other related functions
 * @author Muhammad Abdullah
 */
@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    public UserServiceImpl() { }

    @Override
    public User registerUser(User user) throws ValidationException, DataAccessException {
        if (user.getName().isBlank()) throw new ValidationException("Name is required");
        if (user.getEmail().isBlank()) throw new ValidationException("Email is required");
        if (this.findUserByEmail(user.getEmail()).isPresent()) throw new ValidationException("Email is already registered");
        if (user.getPassword().isBlank() || user.getPassword().length() < 6) throw new ValidationException("Password should have at-least 6 characters");
        if (user.getType() != UserType.MANAGER && user.getType() != UserType.TENNIS_USER) throw new ValidationException("User type is not valid");

        user.setPassword(createHash(user.getPassword()));
        userDao.create(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public boolean authenticate(User u, String password) {
        return validatePassword(password, u.getPassword());
    }

    @Override
    public boolean isManager(User u) {
        return u.getType() == UserType.MANAGER;
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try {
            return userDao.findByEmail(email);
        }catch (NoResultException e){
            return Optional.empty();
        }
    }
    public static String createHash(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(20, new SecureRandom());
        return bCryptPasswordEncoder.encode(password);
    }

    public static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        return createHash(password).equals(correctHash);
    }
}
