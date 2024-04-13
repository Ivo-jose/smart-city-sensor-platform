package br.com.ivogoncalves.smartcitysensorplatform.services;

import br.com.ivogoncalves.smartcitysensorplatform.exceptions.RequiredObjectIsNullException;
import br.com.ivogoncalves.smartcitysensorplatform.exceptions.ResourceNotFoundException;
import br.com.ivogoncalves.smartcitysensorplatform.models.BrightnessSensor;
import br.com.ivogoncalves.smartcitysensorplatform.models.dtos.BrightnessSensorDTO;
import br.com.ivogoncalves.smartcitysensorplatform.repositories.BrightnessSensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class BrightnessSensorService {

    @Autowired
    private BrightnessSensorRepository repository;
    @Autowired
    private ModelMapper mapper;
    private Logger logger = Logger.getLogger(BrightnessSensorService.class.getName());

    
    public BrightnessSensorDTO findById(String id) {
    	logger.info("Finding a brightness sensor...");
    	var entity = repository.findById(id).orElseThrow(() -> 
    					new ResourceNotFoundException("There are no records for this id! ID: " + id));
    	return mapper.map(entity, BrightnessSensorDTO.class);
    }
    
    public List<BrightnessSensorDTO> findAll() {
    	logger.info("Finding all brightness sensor...");
    	return repository.findAll().stream().map(bs -> mapper.map(bs, BrightnessSensorDTO.class)).collect(Collectors.toList());
    }
    
    public BrightnessSensorDTO create(BrightnessSensorDTO objDTO) {
        if(objDTO == null) throw new RequiredObjectIsNullException();
        logger.info("Create a new brightness sensor...");
        var entity = mapper.map(objDTO, BrightnessSensor.class);
        return mapper.map(repository.save(entity), BrightnessSensorDTO.class);
    }
    
    public BrightnessSensorDTO update(BrightnessSensorDTO objDTO) {
        if(objDTO == null) throw new RequiredObjectIsNullException();
        var entity = repository.findById(objDTO.getUid()).orElseThrow(() -> 
			new ResourceNotFoundException("There are no records for this id! UID: " + objDTO.getUid()));
        entity.setLux(objDTO.getLux());
        entity.setTimesStampUTC(objDTO.getTimesStampUTC());
        return mapper.map(repository.save(entity), BrightnessSensorDTO.class);
    	
    }
    
    public void delete(String uid) {
    	if(uid == null) throw new RequiredObjectIsNullException();
    	logger.info("Deleting a brightness sensor ...");
    	var entity = repository.findById(uid).orElseThrow(() -> 
			new ResourceNotFoundException("There are no records for this id! UID: " + uid));
    	repository.delete(entity);
    }
}
