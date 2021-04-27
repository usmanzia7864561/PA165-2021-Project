package muni.pa165;

import muni.pa165.persistence.PersistenceApplicationContext;
import muni.pa165.persistence.dao.CourtDao;
import muni.pa165.persistence.dao.EventDao;
import muni.pa165.persistence.dao.ParticipantDao;
import muni.pa165.persistence.dao.UserDao;
import muni.pa165.persistence.entity.Court;
import muni.pa165.persistence.entity.Event;
import muni.pa165.persistence.entity.Participant;
import muni.pa165.persistence.entity.User;
import muni.pa165.persistence.enums.EventType;
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
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EventTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserDao userDao;

    @Inject
    private EventDao eventDao;

    @Inject
    private CourtDao courtDao;

    @Inject
    private ParticipantDao participantDao;

    private User manager;

    private Participant p1;
    private Participant p2;

    private Event e1;
    private Event e2;

    private Court c1;
    private Court c2;

    public EventTest(){ }

    @BeforeMethod
    public void createEvents(){
        manager = new User("Manager","example@email.com","123456", UserType.MANAGER);
        userDao.create(manager);

        c1 = new Court("Tennis Court","Brno","grass",true);
        c2 = new Court("A1 Block Court","Brno","clay",false);

        courtDao.create(c1);
        courtDao.create(c2);

        p1 = new Participant("Usman");
        p2 = new Participant("Robert");

        participantDao.create(p1);
        participantDao.create(p2);

        e1 = new Event("ABC Tournament", LocalTime.NOON,LocalTime.MIDNIGHT, LocalDate.now(), EventType.TOURNAMENT);
        e2 = new Event("Tennis Lesson", LocalTime.of(8,15),LocalTime.of(10,15), LocalDate.of(2021,12,12), EventType.LESSON);

        e1.setUser(manager);
        e1.setCourt(c2);
        e1.addParticipant(p1);
        e1.addParticipant(p2);

        e2.setUser(manager);
        e2.setCourt(c1);
        e2.addParticipant(p2);

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
    public void fetchUserTest(){
        User user = e1.getUser();
        Assert.assertEquals(user, manager);
    }

    @Test
    public void fetchCourtTest(){
        Assert.assertEquals(e1.getCourt(), c2);
        Assert.assertEquals(e2.getCourt(), c1);
    }

    @Test
    public void fetchParticipantsTest(){
        Assert.assertEquals(e1.getParticipants().size(), 2);
        Assert.assertEquals(e1.getParticipants(), List.of(p1, p2));

        Assert.assertEquals(e2.getParticipants().size(), 1);
        Assert.assertTrue(e2.getParticipants().contains(p2));
        Assert.assertFalse(e2.getParticipants().contains(p1));
    }

    @Test
    public void removeParticipantsTest(){
        Assert.assertEquals(e1.getParticipants().size(), 2);
        Assert.assertTrue(e1.getParticipants().containsAll(List.of(p1, p2)));

        e1.removeParticipant(p2);

        Assert.assertEquals(e1.getParticipants().size(), 1);
        Assert.assertFalse(e1.getParticipants().contains(p2));
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
