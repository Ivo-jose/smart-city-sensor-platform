package br.com.ivogoncalves.smartcitysensorplatform.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    
    public TemperatureSensorDTO findById(String id) {
    	logger.info("Finding a brightness sensor...");
    	var entity = repository.findById(id).orElseThrow(() -> 
    					new ResourceNotFoundException("There are no records for this id! ID: " + id));
    	return mapper.map(entity, TemperatureSensorDTO.class);
    }
    
    public List<TemperatureSensorDTO> findAll() {
    	logger.info("Finding all brightness sensor...");
    	return repository.findAll().stream().map(bs -> mapper.map(bs, TemperatureSensorDTO.class)).collect(Collectors.toList());
    }
    
    public TemperatureSensorDTO create(TemperatureSensorDTO objDTO) {
        if(objDTO == null) throw new RequiredObjectIsNullException();
        logger.info("Create a new brightness sensor...");
        var entity = mapper.map(objDTO, TemperatureSensor.class);
        return mapper.map(repository.save(entity), TemperatureSensorDTO.class);
    }
    
    public TemperatureSensorDTO update(TemperatureSensorDTO objDTO) {
        if(objDTO == null) throw new RequiredObjectIsNullException();
        var entity = repository.findById(objDTO.getUid()).orElseThrow(() -> 
			new ResourceNotFoundException("There are no records for this id! UID: " + objDTO.getUid()));
        entity.setCelsiusTemperature(objDTO.getCelsiusTemperature());
        entity.setUtcTimesStamp(objDTO.getUtcTimesStamp());
        return mapper.map(repository.save(entity), TemperatureSensorDTO.class);
    	
    }
    
    public void delete(String uid) {
    	if(uid == null) throw new RequiredObjectIsNullException();
    	logger.info("Deleting a brightness sensor ...");
    	var entity = repository.findById(uid).orElseThrow(() -> 
			new ResourceNotFoundException("There are no records for this id! UID: " + uid));
    	repository.delete(entity);
    }
}
