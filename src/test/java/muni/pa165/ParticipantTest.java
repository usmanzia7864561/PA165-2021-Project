package muni.pa165;

import muni.pa165.entity.Court;
import muni.pa165.entity.Event;
import muni.pa165.entity.Participant;
import muni.pa165.entity.User;
import muni.pa165.enums.EventType;
import muni.pa165.enums.UserType;

import java.time.LocalDate;
import java.time.LocalTime;

@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class ParticipantTest  extends AbstractTestNGSpringContextTests

{

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public ParticipantTest() { }

    @Test
    public void participantTest(){
        EntityManager entityManager = null;

        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            User ahmad = new User("Ahmad","ahmad@example.com","123456", UserType.MANAGER);
            entityManager.persist(ahmad);

            Event event = new Event("Tennis", LocalTime.now(),LocalTime.now().plusHours(2), LocalDate.now().plusDays(2), EventType.LESSON,ahmad);
            entityManager.persist(event);


            Participant participant = new Participant("Robert Kelly",event);
            entityManager.persist(participant);

            Participant participantFound = entityManager.find(Participant.class, participant.getId());
            assert courtFound.equals(participant);
            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null) entityManager.close();
        }
    }




}
