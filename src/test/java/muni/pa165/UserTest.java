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
 * User Test for Crud
 *
 * @author Usman Zia
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class UserTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public UserTest(){ }

    @Test
    public void userTest(){
        EntityManager entityManager = null;

        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            User manager = new User("Ahmad","ahmad@example.com","123456", UserType.MANAGER);
            entityManager.persist(manager);

            User tennis_user = new User("Tennis","tennis@example.com","123456", UserType.TENNIS_USER);
            entityManager.persist(tennis_user);


            User user_found = entityManager.find(User.class, tennis_user.getId());
            assert user_found.equals(tennis_user);
            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null) entityManager.close();
        }
    }
}
