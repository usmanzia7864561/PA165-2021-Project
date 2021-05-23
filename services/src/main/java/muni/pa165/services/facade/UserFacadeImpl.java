package muni.pa165.services.facade;

import muni.pa165.api.dto.UserAuthenticateDTO;
import muni.pa165.api.dto.UserDTO;
import muni.pa165.api.dto.UserResponseDTO;
import muni.pa165.api.dto.UserUpdateDTO;
import muni.pa165.api.facade.UserFacade;
import muni.pa165.persistence.entity.User;
import muni.pa165.services.UserService;
import muni.pa165.services.converter.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

/**
 * Implementation of UserFacade interface
 * @author Muhammad Abdullah
 */

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    @Autowired
    UserService userService;

    @Autowired
    DozerConverter dozerConverter;

    public UserFacadeImpl(UserService service,DozerConverter converter){
        userService = service;
        dozerConverter = converter;
    }

    @Override
    public UserResponseDTO findUserById(Long userId) {
        Optional<User> user = userService.findUserById(userId);
        return user.map(value -> dozerConverter.convert(value, UserResponseDTO.class)).orElse(null);
    }

    @Override
    public UserResponseDTO findUserByEmail(String email) {
        Optional<User> user = userService.findUserByEmail(email);
        return user.map(value -> dozerConverter.convert(value, UserResponseDTO.class)).orElse(null);
    }

    @Override
    public UserDTO registerUser(UserDTO u) {
        User user = dozerConverter.convert(u, User.class);
        userService.registerUser(user);
        u.setId(user.getId());
        return u;
    }

    @Override
    public Collection<UserResponseDTO> getAllUsers() {
        return dozerConverter.convert(userService.getAllUsers(), UserResponseDTO.class);
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO u) {
        Optional<User> user = userService.findUserByEmail(u.getEmail());
        return user.filter(value -> userService.authenticate(value, u.getPassword())).isPresent();
    }

    @Override
    public boolean delete(long id) {
        return userService.delete(id);
    }

    @Override
    public boolean isManager(UserDTO u) {
        return userService.isManager(dozerConverter.convert(u, User.class));
    }

    @Override
    public UserUpdateDTO update(long id, UserUpdateDTO userUpdateDTO) {
        Optional<User> updatedUser = userService.update(id,dozerConverter.convert(userUpdateDTO, User.class));
        return updatedUser.map(user -> dozerConverter.convert(user, UserUpdateDTO.class)).orElse(null);
    }
}
