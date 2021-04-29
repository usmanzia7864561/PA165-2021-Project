package muni.pa165.services.facade;

import muni.pa165.api.dto.UserAuthenticateDTO;
import muni.pa165.api.dto.UserDTO;
import muni.pa165.api.facade.UserFacade;
import muni.pa165.persistence.entity.User;
import muni.pa165.services.BeanMappingService;
import muni.pa165.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

public class UserFacadeImpl implements UserFacade {
    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public UserDTO findUserById(Long userId) {
        Optional<User> user = userService.findUserById(userId);
        return user.isEmpty() ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        Optional<User> user = userService.findUserByEmail(email);
        return user.isEmpty() ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public void registerUser(UserDTO u, String unencryptedPassword) {
        User userEntity = beanMappingService.mapTo(u, User.class);
        userService.registerUser(userEntity, unencryptedPassword);
        u.setId(userEntity.getId());
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        return beanMappingService.mapTo(userService.getAllUsers(), UserDTO.class);
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO u) {
        Optional<User> user = userService.findUserById(u.getUserId());
        return user.isPresent() && userService.authenticate(user.get(), u.getPassword());
    }

    @Override
    public boolean isManager(UserDTO u) {
        return userService.isManager(beanMappingService.mapTo(u, User.class));
    }
}
