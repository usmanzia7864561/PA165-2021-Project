package muni.pa165;

import muni.pa165.entity.Court;
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

@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class CourtTest extends AbstractTestNGSpringContextTests {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public CourtTest() { }

    @Test
    public void courtTest(){
        EntityManager entityManager = null;

        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();



            Court court = new Court("Golf Court","Brno","grass",true);
            entityManager.persist(court);

            Court courtFound = entityManager.find(Court.class, court.getId());
            assert courtFound.equals(court);
            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null) entityManager.close();
        }
    }
}
