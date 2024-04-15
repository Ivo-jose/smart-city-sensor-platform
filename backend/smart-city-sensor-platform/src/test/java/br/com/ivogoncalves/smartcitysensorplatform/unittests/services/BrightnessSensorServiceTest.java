package br.com.ivogoncalves.smartcitysensorplatform.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import br.com.ivogoncalves.smartcitysensorplatform.exceptions.RequiredObjectIsNullException;
import br.com.ivogoncalves.smartcitysensorplatform.models.BrightnessSensor;
import br.com.ivogoncalves.smartcitysensorplatform.models.dtos.BrightnessSensorDTO;
import br.com.ivogoncalves.smartcitysensorplatform.repositories.BrightnessSensorRepository;
import br.com.ivogoncalves.smartcitysensorplatform.services.BrightnessSensorService;
import br.com.ivogoncalves.smartcitysensorplatform.unittests.mocks.MockBrightnessSensor;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BrightnessSensorServiceTest {
	
	Logger logger = Logger.getLogger(BrightnessSensorServiceTest.class.getName());
	
	MockBrightnessSensor input;
	
	@InjectMocks
	private BrightnessSensorService service;
	@Mock
	private BrightnessSensorRepository repository;
	@Mock
	private ModelMapper mapper;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBrightnessSensor();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFindById() {
		when(mapper.map(Mockito.any(), Mockito.eq(BrightnessSensorDTO.class))).thenAnswer(invocation -> {
            BrightnessSensor bs = invocation.getArgument(0);
            BrightnessSensorDTO dto = new BrightnessSensorDTO();
            dto.setUid(bs.getUid());
            dto.setLux(bs.getLux());
            dto.setTimesStampUTC(bs.getTimesStampUTC());
            return dto;
        });
		 BrightnessSensor bs = input.mockEntity(1);
		when(repository.findById(String.valueOf(Short.MAX_VALUE))).thenReturn(Optional.of(bs));
		var result = service.findById(String.valueOf(Short.MAX_VALUE));
		assertNotNull(result);
		assertNotNull(result.getUid());
		assertNotNull(result.getLux());
		assertNotNull(result.getTimesStampUTC());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/brightness/v1/"+Short.MAX_VALUE +">;rel=\"self\"]"));
		assertEquals(101.0, result.getLux());
	}
	
	
	@Test
	void testCreate() {
	    // Configuração do mock para o método map de ModelMapper
		when(mapper.map(Mockito.any(BrightnessSensor.class), Mockito.eq(BrightnessSensorDTO.class))).thenAnswer(invocation -> {
	        BrightnessSensor entity = invocation.getArgument(0);
	        BrightnessSensorDTO dto = new BrightnessSensorDTO();
	        dto.setUid(entity.getUid());
	        dto.setLux(entity.getLux());
	        dto.setTimesStampUTC(entity.getTimesStampUTC());
	        return dto;
	    });
	    
	    BrightnessSensorDTO  dto = input.mockDTO(1);
	    BrightnessSensor entity = new BrightnessSensor(); 
	    entity.setUid(dto.getUid());
	    entity.setLux(dto.getLux());
	    entity.setTimesStampUTC(dto.getTimesStampUTC());
	    when(repository.save(Mockito.any(BrightnessSensor.class))).thenReturn(entity);
	    var result = service.create(dto);
		assertNotNull(result);
		assertNotNull(result.getUid());
		assertNotNull(result.getLux());
		assertNotNull(result.getTimesStampUTC());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/brightness/v1/"+ (Short.MAX_VALUE + 1) +">;rel=\"self\"]"));
		assertEquals(101.0, result.getLux());
	}
	
	@Test
	void testCreateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {service.create(null);});
		String expectedMessage = "Not allowed to persist a null object";
		assertTrue(exception.getMessage().contains(expectedMessage));
	}
	
	@Test
	void testUpdate() {
	    // Configuração do mock para o método map de ModelMapper
		when(mapper.map(Mockito.any(BrightnessSensor.class), Mockito.eq(BrightnessSensorDTO.class))).thenAnswer(invocation -> {
	        BrightnessSensor entity = invocation.getArgument(0);
	        BrightnessSensorDTO dto = new BrightnessSensorDTO();
	        dto.setUid(entity.getUid());
	        dto.setLux(entity.getLux());
	        dto.setTimesStampUTC(entity.getTimesStampUTC());
	        return dto;
	    });
	    
	    BrightnessSensorDTO  dto = input.mockDTO(1);
	    BrightnessSensor entity = input.mockEntity(1);	    
	    when(repository.findById(dto.getUid())).thenReturn(Optional.of(entity));
	    when(repository.save(Mockito.any(BrightnessSensor.class))).thenReturn(entity);
	    var result = service.update(dto);
		assertNotNull(result);
		assertNotNull(result.getUid());
		assertNotNull(result.getLux());
		assertNotNull(result.getTimesStampUTC());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/brightness/v1/"+ (Short.MAX_VALUE + 1) +">;rel=\"self\"]"));
		assertEquals(101.0, result.getLux());
	}
	
	@Test
	void testUpdateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {service.update(null);});
		String expectedMessage = "Not allowed to persist a null object";
		assertTrue(exception.getMessage().contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		BrightnessSensor entity = input.mockEntity(1);
		when(repository.findById(entity.getUid())).thenReturn(Optional.of(entity));
		service.delete(entity.getUid());
	}
	
	@Test
	void testFindAll() {
		when(mapper.map(Mockito.any(BrightnessSensor.class), Mockito.eq(BrightnessSensorDTO.class))).thenAnswer(invocation -> {
	        BrightnessSensor entity = invocation.getArgument(0);
	        BrightnessSensorDTO dto = new BrightnessSensorDTO();
	        dto.setUid(entity.getUid());
	        dto.setLux(entity.getLux());
	        dto.setTimesStampUTC(entity.getTimesStampUTC());
	        return dto;
	    });
		
		List<BrightnessSensor> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		var sensors = service.findAll();
		assertNotNull(sensors);
		assertEquals(10, sensors.size());
		
		var sensorOne = sensors.get(1);
		assertNotNull(sensorOne);
		assertNotNull(sensorOne.getUid());
		assertNotNull(sensorOne.getLux());
		assertNotNull(sensorOne.getTimesStampUTC());
		assertNotNull(sensorOne.getLinks());
		assertTrue(sensorOne.toString().contains("links: [</api/brightness/v1/"+ sensorOne.getUid() +">;rel=\"self\"]"));
		assertEquals(101.0, sensorOne.getLux());
		
		var sensorThree = sensors.get(3);
		assertNotNull(sensorThree);
		assertNotNull(sensorThree.getUid());
		assertNotNull(sensorThree.getLux());
		assertNotNull(sensorThree.getTimesStampUTC());
		assertNotNull(sensorThree.getLinks());
		assertTrue(sensorThree.toString().contains("links: [</api/brightness/v1/"+ sensorThree.getUid() +">;rel=\"self\"]"));
		assertEquals(103.0, sensorThree.getLux());
		
		var sensorSix = sensors.get(6);
		assertNotNull(sensorSix);
		assertNotNull(sensorSix.getUid());
		assertNotNull(sensorSix.getLux());
		assertNotNull(sensorSix.getTimesStampUTC());
		assertNotNull(sensorSix.getLinks());
		assertTrue(sensorSix.toString().contains("links: [</api/brightness/v1/"+ sensorSix.getUid() +">;rel=\"self\"]"));
		assertEquals(106.0, sensorSix.getLux());
	}
}
