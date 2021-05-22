package muni.pa165.services.facade;

import muni.pa165.api.dto.UserAuthenticateDTO;
import muni.pa165.api.dto.UserDTO;
import muni.pa165.api.facade.UserFacade;
import muni.pa165.persistence.entity.User;
import muni.pa165.services.converter.DozerConverter;
import muni.pa165.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

/**
 * Implementation of UserFacade interface
 * @author Muhammad Abdullah
 */

@Service
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private UserService userService;

    @Inject
    private DozerConverter dozerConverter;

    public UserFacadeImpl(UserService service,DozerConverter converter){
        userService = service;
        dozerConverter = converter;
    }

    @Override
    public UserDTO findUserById(Long userId) {
        Optional<User> user = userService.findUserById(userId);
        return dozerConverter.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        Optional<User> user = userService.findUserByEmail(email);
        return dozerConverter.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO registerUser(UserDTO u) {
        User userEntity = dozerConverter.convert(u, User.class);
        userService.registerUser(userEntity);
        u.setId(userEntity.getId());

        return u;
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        return dozerConverter.convert(userService.getAllUsers(), UserDTO.class);
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO u) {
        Optional<User> user = userService.findUserByEmail(u.getEmail());
        return user.isPresent() && userService.authenticate(user.get(), u.getPassword());
    }

    @Override
    public boolean isManager(UserDTO u) {
        return userService.isManager(dozerConverter.convert(u, User.class));
    }
}
