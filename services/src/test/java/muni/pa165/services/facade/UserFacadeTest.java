package muni.pa165.services.facade;

import muni.pa165.api.dto.UserAuthenticateDTO;
import muni.pa165.api.dto.UserDTO;
import muni.pa165.api.facade.UserFacade;
import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.UserType;
import muni.pa165.services.UserService;
import muni.pa165.services.config.ServiceConfig;
import muni.pa165.services.converter.DozerConverter;
import muni.pa165.services.converter.DozerConverterImpl;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfig.class)
public class UserFacadeTest {
    @Inject
    private UserFacade userFacade;

    UserService userService = mock(UserService.class);

    @Inject
    private DozerConverter converter;

    private final UserDTO managerDTO = new UserDTO("Manager","manager@email.com","123456", UserType.MANAGER);
    private final UserAuthenticateDTO managerAuthDTO = new UserAuthenticateDTO("manager@email.com","123456");
    private User manager;

    @BeforeClass
    public void setup(){
        converter = new DozerConverterImpl();
        manager = converter.convert(managerDTO, User.class);

        userFacade = new UserFacadeImpl(userService,converter);
    }

    @BeforeMethod
    public void initServiceMocks(){
        when(userService.findUserByEmail(anyString())).thenReturn(Optional.of(manager));
        when(userService.authenticate(any(), anyString())).thenReturn(true);
        when(userService.registerUser(any())).thenReturn(manager);
    }

    @Test
    public void registerUserTest(){
        UserDTO result = userFacade.registerUser(managerDTO);
        Assert.assertEquals(result, managerDTO);
    }

    @Test
    public void loginUserTest(){
        boolean isLoggedIn = userFacade.authenticate(managerAuthDTO);
        Assert.assertTrue(isLoggedIn);
    }
}
