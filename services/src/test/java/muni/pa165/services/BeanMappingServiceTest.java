package muni.pa165.services;

import muni.pa165.api.dto.UserDTO;
import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.UserType;
import muni.pa165.services.config.ServiceConfig;
import muni.pa165.services.converter.DozerConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean Mapping service. Mostly taken from the example project
 *
 * @author Muhammad Abdullah
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests
{
    @Inject
    private DozerConverter dozerConverter;

    private final List<User> users = new ArrayList<>();

    @BeforeMethod
    public void createUsers(){
		User manager = new User("Robert","robert@email.com","123456", UserType.MANAGER);
		users.add(manager);
    }
    
    @Test
    public void shouldMapInnerEvents(){
    	List<UserDTO> userDTOS = dozerConverter.convert(users, UserDTO.class);
        Assert.assertEquals(userDTOS.size(), users.size());
        Assert.assertEquals(userDTOS.get(0).getEmail(), users.get(0).getEmail());
    }
}
