package muni.pa165.services;

import muni.pa165.persistence.dao.ParticipantDao;
import muni.pa165.persistence.entity.Participant;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ParticipantServiceTest {
    @Mock
    private ParticipantDao participantDao;

    @Autowired
    @InjectMocks
    private ParticipantService participantService;

    private Participant p1;
    private Participant p2;

    @BeforeClass
    public void setup(){
        participantService = new ParticipantServiceImpl();

        p1 = new Participant("Ahmad");
        p2 = new Participant("Gandhi");

        MockitoAnnotations.openMocks(this);
    }

    @BeforeMethod
    public void beforeTest(){
        when(participantDao.create(any())).thenReturn(Optional.of(p1));
        when(participantDao.findAll()).thenReturn(List.of(p1,p2));
        when(participantDao.findById(anyLong())).thenReturn(Optional.of(p1));
        when(participantDao.findByName(anyString())).thenReturn(List.of(p2));
    }

    @Test
    public void createParticipantTest(){
        Optional<Participant> participant = participantService.create(p1);
        Assert.assertTrue(participant.isPresent());
        Assert.assertEquals(participant.get(),p1);
    }

    @Test
    public void fetchAllTest(){
        List<Participant> participants = participantService.getAllParticipants();
        Assert.assertEquals(participants,List.of(p1,p2));
    }

    @Test
    public void fetchByIdTest(){
        Optional<Participant> participant = participantService.findById(12L);

        Assert.assertTrue(participant.isPresent());
        Assert.assertEquals(participant,Optional.of(p1));
        Assert.assertNotEquals(participant,Optional.of(p2));
    }

    @Test
    public void fetchByNameTest(){
        List<Participant> participants = participantService.findByName("Gandhi");

        Assert.assertFalse(participants.isEmpty());
        Assert.assertTrue(participants.contains(p2));
        Assert.assertFalse(participants.contains(p1));
    }
}
