package muni.pa165.services;

import muni.pa165.api.dto.UserDTO;
import muni.pa165.api.facade.UserFacade;
import muni.pa165.persistence.enums.UserType;
import muni.pa165.services.config.ServiceConfig;
import muni.pa165.services.facade.UserFacadeImpl;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfig.class)
public class EventFacadeTest {
    @InjectMocks
    private UserFacade userFacade;

    @BeforeClass
    public void setup(){
        userFacade = new UserFacadeImpl();
    }

    @Test
    public void registerUserTest(){
        UserDTO manager = new UserDTO("Manager","manager@email.com","123456", UserType.MANAGER);
        userFacade.registerUser(manager);
    }
}
