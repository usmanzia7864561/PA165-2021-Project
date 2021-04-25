package muni.pa165;

import muni.pa165.dao.CourtDao;
import muni.pa165.entity.Court;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
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
 * Court Test
 *
 * @author Muhammad Abdullah
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@Transactional
public class CourtTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private CourtDao courtDao;

    private Court c1;
    private Court c2;

    public CourtTest() { }

    @BeforeMethod
    public void createCourts(){
        c1 = new Court("Tennis Court","Brno","grass",true);
        c2 = new Court("A1 Block Court","Brno","clay",false);

        courtDao.create(c1);
        courtDao.create(c2);
    }

    @Test
    public void fetchAllTest(){
        List<Court> courts = courtDao.findAll();

        Assert.assertTrue(courts.containsAll(List.of(c1,c2)));
    }

    @Test
    public void fetchById(){
        Optional<Court> grassCourt = courtDao.findById(c1.getId());

        Assert.assertTrue(grassCourt.isPresent());
        Assert.assertEquals(grassCourt, Optional.of(c1));
    }

    @Test
    public void removeTest(){
        Assert.assertEquals(courtDao.findAll().size(),2);

        courtDao.remove(c1);
        Assert.assertEquals(courtDao.findAll().size(),1);

        courtDao.remove(c2);
        Assert.assertEquals(courtDao.findAll().size(),0);
    }
}
