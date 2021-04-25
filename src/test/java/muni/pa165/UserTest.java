package muni.pa165;

import muni.pa165.dao.EventDao;
import muni.pa165.dao.UserDao;
import muni.pa165.entity.Event;
import muni.pa165.entity.User;
import muni.pa165.enums.EventType;
import muni.pa165.enums.UserType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


/**
 * User Test for Crud
 *
 * @author Usman Zia
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class UserTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager entitymanager;

    @Inject
    private  UserDao userDao;


    private User manager;

    private User participant;

    private User participant1;



    public UserTest(){ }

    @BeforeMethod
    public void createAndFindUser(){
        // Save Manager
        manager = new User("Manager","example888@email.com","123456", UserType.MANAGER);
        userDao.create(manager);
        //Save Participant
        participant = new User("Participant","participant55@email.com","123456", UserType.TENNIS_USER);
        userDao.create(participant);

        List<User> storedUsers = userDao.findAll();
        Assert.assertTrue(storedUsers.containsAll(List.of(manager, participant)));
    }


    @Test
    public void fetchAllTest(){
        List<User> users = userDao.findAll();
        Assert.assertTrue(users.containsAll(List.of(manager, participant)));
    }

    @Test
    public void fetchByIdTest(){
        Optional<User> users = userDao.findById(manager.getId());

        Assert.assertTrue(users.isPresent());
        Assert.assertEquals(users, Optional.of(manager));
    }

    @Test
    public void removeTest(){
        Assert.assertEquals(userDao.findAll().size(),2);

        userDao.remove(participant);
        Assert.assertEquals(userDao.findAll().size(),1);

        userDao.remove(manager);
        Assert.assertEquals(userDao.findAll().size(),0);
    }

}
