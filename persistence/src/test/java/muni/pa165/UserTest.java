package muni.pa165;

import muni.pa165.persistence.PersistenceApplicationContext;
import muni.pa165.persistence.dao.UserDao;
import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.UserType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


/**
 * User Test for Crud
 *
 * @author Usman Zia
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager entitymanager;

    @Inject
    private  UserDao userDao;

    private User manager;
    private User participant;

    public UserTest(){ }

    @BeforeMethod
    public void createAndFindUser(){
        // Save Manager
        manager = new User("Manager","example814785@email.com","123456", UserType.MANAGER);
        userDao.create(manager);
        //Save Participant
        participant = new User("Participant","participant551djdk@email.com","123456", UserType.TENNIS_USER);
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
