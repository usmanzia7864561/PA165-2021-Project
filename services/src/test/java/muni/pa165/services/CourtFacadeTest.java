package muni.pa165.services;

import muni.pa165.api.dto.CourtDTO;
import muni.pa165.api.facade.CourtFacade;
import muni.pa165.services.config.ServiceConfig;
import muni.pa165.services.facade.CourtFacadeImpl;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfig.class)
public class CourtFacadeTest {
    @InjectMocks
    private CourtFacade courtFacade;

    @BeforeClass
    public void setup(){
        courtFacade = new CourtFacadeImpl();
    }

    @Test
    public void createCourtTest(){
        CourtDTO court;
        // court = new CourtDTO("Tennis Court","Brno","grass",true);
        // courtFacade.createCourt(court);
    }
}