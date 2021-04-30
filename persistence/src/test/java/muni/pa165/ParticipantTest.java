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
import org.testng.annotations.Ignore;
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
 * Participant test for CRUD
 *
 * @author Muhammad Usman
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ParticipantTest  extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private EventDao eventDao;

    @Inject
    private UserDao userDao;

    @Inject
    private CourtDao courtDao;

    @Inject
    private ParticipantDao participantDao;

    private Participant p1;
    private Participant p2;

    public ParticipantTest() { }

    @BeforeMethod
    public void createParticipants(){
        User manager = new User("Manager","example@email.com","123456", UserType.MANAGER);
        Court c1 = new Court("Tennis Court","Brno","grass",true);

        p1 = new Participant("Usman");
        p2 = new Participant("Robert");

        courtDao.create(c1);
        userDao.create(manager);

        participantDao.create(p1);
        participantDao.create(p2);

        Event e1 = new Event("ABC Tournament", LocalTime.NOON, LocalTime.MIDNIGHT, LocalDate.now(), EventType.TOURNAMENT);
        e1.setUser(manager);
        e1.setCourt(c1);
        e1.addParticipant(p1);
        e1.addParticipant(p2);

        eventDao.create(e1);
    }

    @Test
    public void fetchAllTest(){
        List<Participant> participants = participantDao.findAll();
        Assert.assertTrue(participants.containsAll(List.of(p1, p2)));
    }

    @Test
    public void fetchByIdTest(){
        Optional<Participant> participant = participantDao.findById(p1.getId());

        Assert.assertTrue(participant.isPresent());
        Assert.assertEquals(participant, Optional.of(p1));
    }

    @Ignore
    @Test
    public void removeTest(){
        Assert.assertEquals(participantDao.findAll().size(),2);

        participantDao.remove(p1);
        Assert.assertEquals(participantDao.findAll().size(),1);

        participantDao.remove(p2);
        Assert.assertEquals(participantDao.findAll().size(),0);
    }
}
