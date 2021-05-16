package muni.pa165.services;

import muni.pa165.persistence.dao.UserDao;
import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.UserType;
import muni.pa165.services.config.ServiceConfig;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Testing user service
 * @author Muhammad Abdullah
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Autowired
    @InjectMocks
    private UserService userService;

    private User manager;
    private User tennisPlayer;

    public User getManager(){
        return new User("Manager","manager@email.com","123456", UserType.MANAGER);
    }

    public User getTennisPlayer(){
        return new User("Tennis Player","player@email.com","123456", UserType.TENNIS_USER);
    }

    @BeforeClass
    public void setup() throws ServiceException
    {
        userService = new UserServiceImpl();
        MockitoAnnotations.openMocks(this);
    }

    @Ignore
    @Test
    public void registerUser(){
        manager = userService.registerUser(getManager());
        tennisPlayer =userService.registerUser(getTennisPlayer());

        Assert.assertNotNull(manager);
        Assert.assertNotNull(tennisPlayer);
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void registerUserWithoutName() throws ValidationException{
        manager = new User("","manager@email.com","123456", UserType.MANAGER);
        userService.registerUser(manager);
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void registerUserWithoutEmail() throws ValidationException{
        manager = new User("Manager","","123456", UserType.MANAGER);
        userService.registerUser(manager);
    }

    @Test(expectedExceptions = {ValidationException.class})
    public void registerUserWithoutPassword() throws ValidationException{
        User manager = getManager();
        manager.setPassword("");
        userService.registerUser(manager);
    }

    @Test
    public void passwordHashTest(){
        String password = "123456";
        String hash = UserServiceImpl.createHash(password);

        Assert.assertTrue(UserServiceImpl.validatePassword(password, hash));
    }

    @Test
    public void loginTest(){
        // manager = userService.registerUser(getManager());
        // tennisPlayer = userService.registerUser(getTennisPlayer());

        Assert.assertFalse(userService.authenticate(manager,"12345612"));
        Assert.assertFalse(userService.authenticate(manager,"12345"));
        Assert.assertTrue(userService.authenticate(manager,"123456"));

        Assert.assertFalse(userService.authenticate(tennisPlayer,"12345612"));
        Assert.assertFalse(userService.authenticate(tennisPlayer,"1234567"));
        Assert.assertTrue(userService.authenticate(tennisPlayer,"123456"));
    }

    @Test
    public void findUserByEmailTest(){
        manager  = userService.registerUser(getManager());
        when(userService.findUserByEmail(getManager().getEmail())).thenReturn(Optional.of(getManager()));

        Optional<User> userByEmail = userService.findUserByEmail(manager.getEmail());

        Assert.assertTrue(userByEmail.isPresent());
        Assert.assertEquals(manager.getEmail(), userByEmail.get().getEmail());
    }

    @Test
    public void findUserByIdTest(){
        when(userService.findUserById(manager.getId())).thenReturn(Optional.of(getManager()));

        Optional<User> userById = userService.findUserById(manager.getId());


        Assert.assertTrue(userById.isPresent());
        Assert.assertEquals(manager.getId(), userById.get().getId());
    }

    @Test
    public void userTypeTest(){
        Assert.assertTrue(userService.isManager(getManager()));
        Assert.assertFalse(userService.isManager(getTennisPlayer()));
    }

    @Test
    public void fetchAllUsersTest(){
        manager = userService.registerUser(getManager());
        tennisPlayer = userService.registerUser(getTennisPlayer());

        when(userService.getAllUsers()).thenReturn(List.of(manager,tennisPlayer));

        List<User> allUsers = userService.getAllUsers();

        Assert.assertEquals(allUsers.size(),2);
        Assert.assertTrue(allUsers.contains(manager));
    }
}
