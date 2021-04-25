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

import javax.inject.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Event test for CRUD
 *
 * @author Muhammad Abdullah
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class EventTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserDao userDao;

    @Inject
    private EventDao eventDao;

    private User manager;

    private Event e1;
    private Event e2;

    public EventTest(){ }

    @BeforeMethod
    public void createEvents(){
        manager = new User("Manager","example@email.com","123456", UserType.MANAGER);
        userDao.create(manager);

        e1 = new Event("ABC Tournament", LocalTime.NOON,LocalTime.MIDNIGHT, LocalDate.now(), EventType.TOURNAMENT, manager);
        e2 = new Event("Tennis Lesson", LocalTime.of(8,15),LocalTime.of(10,15), LocalDate.of(2021,12,12), EventType.LESSON, manager);
        eventDao.create(e1);
        eventDao.create(e2);
    }

    @Test
    public void fetchAllTest(){
        List<Event> events = eventDao.findAll();
        Assert.assertTrue(events.containsAll(List.of(e1, e2)));
    }

    @Test
    public void fetchByIdTest(){
        Optional<Event> event = eventDao.findById(e1.getId());

        Assert.assertTrue(event.isPresent());
        Assert.assertEquals(event, Optional.of(e1));
    }

    @Test
    public void removeTest(){
        Assert.assertEquals(eventDao.findAll().size(),2);

        eventDao.remove(e1);
        Assert.assertEquals(eventDao.findAll().size(),1);

        eventDao.remove(e2);
        Assert.assertEquals(eventDao.findAll().size(),0);
    }
}
