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
import br.com.ivogoncalves.smartcitysensorplatform.models.TemperatureSensor;
import br.com.ivogoncalves.smartcitysensorplatform.models.dtos.TemperatureSensorDTO;
import br.com.ivogoncalves.smartcitysensorplatform.repositories.TemperatureSensorRepository;
import br.com.ivogoncalves.smartcitysensorplatform.services.TemperatureSensorService;
import br.com.ivogoncalves.smartcitysensorplatform.unittests.mocks.MockTemperatureSensor;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TemperatureSensorServiceTest {
	
	Logger logger = Logger.getLogger(TemperatureSensorServiceTest.class.getName());
	
	MockTemperatureSensor input;
	
	@InjectMocks
	private TemperatureSensorService service;
	@Mock
	private TemperatureSensorRepository repository;
	@Mock
	private ModelMapper mapper;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockTemperatureSensor();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFindById() {
		when(mapper.map(Mockito.any(), Mockito.eq(TemperatureSensorDTO.class))).thenAnswer(invocation -> {
            TemperatureSensor bs = invocation.getArgument(0);
            TemperatureSensorDTO dto = new TemperatureSensorDTO();
            dto.setUid(bs.getUid());
            dto.setCelsiusTemperature(bs.getCelsiusTemperature());
            dto.setUtcTimesStamp(bs.getUtcTimesStamp());
            return dto;
        });
		 TemperatureSensor bs = input.mockEntity(1);
		when(repository.findById(String.valueOf(Short.MAX_VALUE))).thenReturn(Optional.of(bs));
		var result = service.findById(String.valueOf(Short.MAX_VALUE));
		assertNotNull(result);
		assertNotNull(result.getUid());
		assertNotNull(result.getCelsiusTemperature());
		assertNotNull(result.getUtcTimesStamp());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/temperature/v1/"+Short.MAX_VALUE +">;rel=\"self\"]"));
		assertEquals(41.0, result.getCelsiusTemperature());
	}
	
	
	@Test
	void testCreate() {
	    // Configuração do mock para o método map de ModelMapper
		when(mapper.map(Mockito.any(TemperatureSensor.class), Mockito.eq(TemperatureSensorDTO.class))).thenAnswer(invocation -> {
	        TemperatureSensor entity = invocation.getArgument(0);
	        TemperatureSensorDTO dto = new TemperatureSensorDTO();
	        dto.setUid(entity.getUid());
            dto.setCelsiusTemperature(entity.getCelsiusTemperature());
            dto.setUtcTimesStamp(entity.getUtcTimesStamp());
            return dto;
	    });
	    
	    TemperatureSensorDTO  dto = input.mockDTO(1);
	    TemperatureSensor entity = new TemperatureSensor(); 
	    entity.setUid(dto.getUid());
	    entity.setCelsiusTemperature(dto.getCelsiusTemperature());
	    entity.setUtcTimesStamp(dto.getUtcTimesStamp());
	    when(repository.save(Mockito.any(TemperatureSensor.class))).thenReturn(entity);
	    var result = service.create(dto);
		assertNotNull(result);
		assertNotNull(result.getUid());
		assertNotNull(result.getCelsiusTemperature());
		assertNotNull(result.getUtcTimesStamp());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/temperature/v1/"+ (Short.MAX_VALUE + 1) +">;rel=\"self\"]"));
		assertEquals(41.0, result.getCelsiusTemperature());
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
		when(mapper.map(Mockito.any(TemperatureSensor.class), Mockito.eq(TemperatureSensorDTO.class))).thenAnswer(invocation -> {
	        TemperatureSensor entity = invocation.getArgument(0);
	        TemperatureSensorDTO dto = new TemperatureSensorDTO();
	        dto.setUid(entity.getUid());
	        dto.setCelsiusTemperature(entity.getCelsiusTemperature());
	        dto.setUtcTimesStamp(entity.getUtcTimesStamp());
	        return dto;
	    });
	    
	    TemperatureSensorDTO  dto = input.mockDTO(1);
	    TemperatureSensor entity = input.mockEntity(1);	    
	    when(repository.findById(dto.getUid())).thenReturn(Optional.of(entity));
	    when(repository.save(Mockito.any(TemperatureSensor.class))).thenReturn(entity);
	    var result = service.update(dto);
		assertNotNull(result);
		assertNotNull(result.getUid());
		assertNotNull(result.getCelsiusTemperature());
		assertNotNull(result.getUtcTimesStamp());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/temperature/v1/"+ (Short.MAX_VALUE + 1) +">;rel=\"self\"]"));
		assertEquals(41.0, result.getCelsiusTemperature());
	}
	
	@Test
	void testUpdateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {service.update(null);});
		String expectedMessage = "Not allowed to persist a null object";
		assertTrue(exception.getMessage().contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		TemperatureSensor entity = input.mockEntity(1);
		when(repository.findById(entity.getUid())).thenReturn(Optional.of(entity));
		service.delete(entity.getUid());
	}
	
	@Test
	void testFindAll() {
		when(mapper.map(Mockito.any(TemperatureSensor.class), Mockito.eq(TemperatureSensorDTO.class))).thenAnswer(invocation -> {
	        TemperatureSensor entity = invocation.getArgument(0);
	        TemperatureSensorDTO dto = new TemperatureSensorDTO();
	        dto.setUid(entity.getUid());
	        dto.setCelsiusTemperature(entity.getCelsiusTemperature());
	        dto.setUtcTimesStamp(entity.getUtcTimesStamp());
	        return dto;
	    });
		
		List<TemperatureSensor> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		var sensors = service.findAll();
		assertNotNull(sensors);
		assertEquals(10, sensors.size());
		
		var sensorOne = sensors.get(1);
		assertNotNull(sensorOne);
		assertNotNull(sensorOne.getUid());
		assertNotNull(sensorOne.getCelsiusTemperature());
		assertNotNull(sensorOne.getUtcTimesStamp());
		assertNotNull(sensorOne.getLinks());
		assertTrue(sensorOne.toString().contains("links: [</api/temperature/v1/"+ sensorOne.getUid() +">;rel=\"self\"]"));
		assertEquals(41.0, sensorOne.getCelsiusTemperature());
		
		var sensorThree = sensors.get(3);
		assertNotNull(sensorThree);
		assertNotNull(sensorThree.getUid());
		assertNotNull(sensorThree.getCelsiusTemperature());
		assertNotNull(sensorThree.getUtcTimesStamp());
		assertNotNull(sensorThree.getLinks());
		assertTrue(sensorThree.toString().contains("links: [</api/temperature/v1/"+ sensorThree.getUid() +">;rel=\"self\"]"));
		assertEquals(43.0, sensorThree.getCelsiusTemperature());
		
		var sensorSix = sensors.get(6);
		assertNotNull(sensorSix);
		assertNotNull(sensorSix.getUid());
		assertNotNull(sensorSix.getCelsiusTemperature());
		assertNotNull(sensorSix.getUtcTimesStamp());
		assertNotNull(sensorSix.getLinks());
		assertTrue(sensorSix.toString().contains("links: [</api/temperature/v1/"+ sensorSix.getUid() +">;rel=\"self\"]"));
		assertEquals(46.0, sensorSix.getCelsiusTemperature());
	}
}
