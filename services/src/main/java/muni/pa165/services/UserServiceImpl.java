package muni.pa165.services;

import muni.pa165.api.dto.UserResponseDTO;
import muni.pa165.persistence.dao.UserDao;
import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserDao userDao;

    public UserServiceImpl() { }

    @Override
    public User registerUser(User user) throws ValidationException, DataAccessException {
        if (this.findUserByEmail(user.getEmail()).isPresent()) throw new ValidationException("Email is already registered");
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
        System.out.println("findUserById " + userId);
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

    @Override
    public boolean delete(long id) {
        Optional<User> user = userDao.findById(id);
        if (user.isPresent()){
            userDao.remove(user.get());
            return true;
        }
        return false;
    }

    @Override
    public User update(long id, User user) {
        return userDao.update(id,user);
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
