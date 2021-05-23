package muni.pa165.persistence;

import muni.pa165.persistence.dao.CourtDao;
import muni.pa165.persistence.entity.Court;
import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.EventType;
import muni.pa165.persistence.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Court Test
 *
 * @author Muhammad Abdullah
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CourtTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CourtDao courtDao;

    private Court c1;
    private Court c2;

    private Event e1;
    private Event e2;

    public CourtTest() { }

    @BeforeMethod
    public void createCourts(){
        User manager = new User("Manager", "example@email.com", "12345678", UserType.MANAGER);

        e1 = new Event("ABC Tournament", LocalTime.NOON,LocalTime.MIDNIGHT, LocalDate.now(), EventType.TOURNAMENT);
        e2 = new Event("Tennis Lesson", LocalTime.of(8,15),LocalTime.of(10,15), LocalDate.of(2021,12,12), EventType.LESSON);

        c1 = new Court("Tennis Court","Brno","grass",true);
        c2 = new Court("A1 Block Court","Brno","clay",false);

        e1.setUser(manager);

        c1.addEvent(e1);
        c1.addEvent(e2);

        courtDao.create(c1);
        courtDao.create(c2);
    }


    @Test
    public void fetchById(){
        Optional<Court> grassCourt = courtDao.findById(c1.getId());

        Assert.assertTrue(grassCourt.isPresent());
        Assert.assertEquals(grassCourt, Optional.of(c1));
    }

    @Test
    void courtEventsTest(){
        Set<Event> c1Events = c1.getEvents();
        Set<Event> c2Events = c2.getEvents();

        Assert.assertTrue(c1Events.containsAll(List.of(e1,e2)));
        Assert.assertTrue(c2Events.isEmpty());
    }

    @Test
    void courtEventsRemoveTest(){
        c1.removeEvent(e1);

        Set<Event> c1Events = c1.getEvents();
        Assert.assertEquals(c1Events.size(), 1);
        Assert.assertTrue(c1Events.contains(e2));
        Assert.assertFalse(c1Events.contains(e1));
    }


}
