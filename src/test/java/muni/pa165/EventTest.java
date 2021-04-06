package muni.pa165;

import muni.pa165.entity.Event;
import muni.pa165.entity.User;
import muni.pa165.enums.EventType;
import muni.pa165.enums.UserType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event test for CRUD
 *
 * @author Muhammad Abdullah
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class EventTest extends AbstractTestNGSpringContextTests {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public EventTest(){ }

    @Test
    public void eventTest(){
        EntityManager entityManager = null;

        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            User ahmad = new User("Ahmad","ahmad@example.com","123456", UserType.MANAGER);
            entityManager.persist(ahmad);

            Event event = new Event("Tennis",LocalTime.now(),LocalTime.now().plusHours(2), LocalDate.now().plusDays(2), EventType.LESSON,ahmad);
            entityManager.persist(event);

            System.out.println(event.toString());
            Event tennisEvent = entityManager.find(Event.class, event.getId());
            assert tennisEvent.equals(event);
            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null) entityManager.close();
        }
    }
}
