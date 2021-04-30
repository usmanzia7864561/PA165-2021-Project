package muni.pa165.services.facade;

import muni.pa165.api.dto.ParticipantDTO;
import muni.pa165.api.facade.ParticipantFacade;
import muni.pa165.persistence.entity.Participant;
import muni.pa165.services.ParticipantService;
import muni.pa165.services.config.ServiceConfig;
import muni.pa165.services.converter.DozerConverter;
import muni.pa165.services.converter.DozerConverterImpl;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfig.class)
public class ParticipantFacadeTest {
    @Inject
    private ParticipantFacade participantFacade;
    @Inject
    private DozerConverter converter;

    ParticipantService participantService = mock(ParticipantService.class);

    ParticipantDTO participantDTO = new ParticipantDTO("Usman");
    Participant participant;

    @BeforeClass
    public void setup(){
        converter = new DozerConverterImpl();
        participant = converter.convert(participantDTO,Participant.class);
        participantFacade = new ParticipantFacadeImpl(participantService,converter);
    }

    @BeforeMethod
    public void beforeMethodMock(){
        when(participantService.findById(anyLong())).thenReturn(Optional.of(participant));
    }

    @Ignore
    @Test
    public void fetchByIdTest(){
        ParticipantDTO p1 = participantFacade.findById(participantDTO.getId());
        Assert.assertEquals(p1.getName(), participantDTO.getName());
    }
}
