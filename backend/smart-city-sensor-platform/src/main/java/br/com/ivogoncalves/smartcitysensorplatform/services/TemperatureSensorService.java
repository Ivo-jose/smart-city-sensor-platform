package br.com.ivogoncalves.smartcitysensorplatform.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivogoncalves.smartcitysensorplatform.controllers.TemperatureSensorController;
import br.com.ivogoncalves.smartcitysensorplatform.exceptions.RequiredObjectIsNullException;
import br.com.ivogoncalves.smartcitysensorplatform.exceptions.ResourceNotFoundException;
import br.com.ivogoncalves.smartcitysensorplatform.models.TemperatureSensor;
import br.com.ivogoncalves.smartcitysensorplatform.models.dtos.TemperatureSensorDTO;
import br.com.ivogoncalves.smartcitysensorplatform.repositories.TemperatureSensorRepository;

@Service
public class TemperatureSensorService {

    @Autowired
    private TemperatureSensorRepository repository;
    @Autowired
    private ModelMapper mapper;
    private Logger logger = Logger.getLogger(TemperatureSensorService.class.getName());

    // Retrieves a brightness sensor by its unique identifier
    public TemperatureSensorDTO findById(String uid) {
    	logger.info("Finding a brightness sensor...");
    	var entity = repository.findById(uid).orElseThrow(() -> 
    					new ResourceNotFoundException("There are no records for this id! ID: " + uid));
    	TemperatureSensorDTO dtoObj = mapper.map(entity, TemperatureSensorDTO.class);
    	dtoObj.add(linkTo(methodOn(TemperatureSensorController.class).findByID(uid)).withSelfRel());
    	return dtoObj;
    }
    
    // Retrieves all brightness sensors
    public List<TemperatureSensorDTO> findAll() {
    	logger.info("Finding all brightness sensor...");
    	var listDTO = repository.findAll().stream().map(bs -> mapper.map(bs, TemperatureSensorDTO.class)).collect(Collectors.toList());
    	listDTO.stream().forEach(ts -> ts.add(linkTo(methodOn(TemperatureSensorController.class).findByID(ts.getUid())).withSelfRel()));
    	return listDTO;
    }
    
    // Creates a new brightness sensor with the provided data
    public TemperatureSensorDTO create(TemperatureSensorDTO objDTO) {
        if(objDTO == null) throw new RequiredObjectIsNullException();
        logger.info("Create a new brightness sensor...");
        TemperatureSensor entity = new TemperatureSensor();
        entity.setUid(objDTO.getUid());
        entity.setCelsiusTemperature(objDTO.getCelsiusTemperature());
        entity.setUtcTimesStamp(objDTO.getUtcTimesStamp());
        TemperatureSensorDTO dtoObj = mapper.map(repository.save(entity), TemperatureSensorDTO.class);
        dtoObj.add(linkTo(methodOn(TemperatureSensorController.class).findByID(dtoObj.getUid())).withSelfRel());
    	return dtoObj;
    }
    
    // Updates an existing brightness sensor with the provided data
    public TemperatureSensorDTO update(TemperatureSensorDTO objDTO) {
        if(objDTO == null) throw new RequiredObjectIsNullException();
        var entity = repository.findById(objDTO.getUid()).orElseThrow(() -> 
			new ResourceNotFoundException("There are no records for this id! UID: " + objDTO.getUid()));
        entity.setCelsiusTemperature(objDTO.getCelsiusTemperature());
        entity.setUtcTimesStamp(objDTO.getUtcTimesStamp());
        TemperatureSensorDTO dtoObj = mapper.map(repository.save(entity), TemperatureSensorDTO.class);
        dtoObj.add(linkTo(methodOn(TemperatureSensorController.class).findByID(dtoObj.getUid())).withSelfRel());
    	return dtoObj;
    	
    }
    
    public void delete(String uid) {
    	if(uid == null) throw new RequiredObjectIsNullException();
    	logger.info("Deleting a brightness sensor ...");
    	var entity = repository.findById(uid).orElseThrow(() -> 
			new ResourceNotFoundException("There are no records for this id! UID: " + uid));
    	repository.delete(entity);
    }
}
