package br.com.ivogoncalves.smartcitysensorplatform.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.ivogoncalves.smartcitysensorplatform.controllers.BrightnessSensorController;
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

    
    public BrightnessSensorDTO findById(String uid) {
    	logger.info("Finding a brightness sensor...");
    	var entity = repository.findById(uid).orElseThrow(() -> 
    					new ResourceNotFoundException("There are no records for this id! ID: " + uid));
    	BrightnessSensorDTO  objDTO = mapper.map(entity, BrightnessSensorDTO.class);
    	objDTO.add(linkTo(methodOn(BrightnessSensorController.class).findByID(uid)).withSelfRel());
    	return objDTO;
    }
    
    public List<BrightnessSensorDTO> findAll() {
    	logger.info("Finding all brightness sensor...");
    	var listDTO = repository.findAll().stream().map
    							(bs -> mapper.map(bs, BrightnessSensorDTO.class)).collect(Collectors.toList());
    	listDTO.stream().forEach(bs -> bs.add(linkTo(methodOn(BrightnessSensorController.class).findByID(bs.getUid())).withSelfRel()));
    	return listDTO;
    }
    
    public BrightnessSensorDTO create(BrightnessSensorDTO objDTO) {
        if(objDTO == null) throw new RequiredObjectIsNullException();
        logger.info("Create a new brightness sensor...");
        var entity = mapper.map(objDTO, BrightnessSensor.class);
        BrightnessSensorDTO  dtoObj =  mapper.map(repository.save(entity), BrightnessSensorDTO.class);
        dtoObj.add(linkTo(methodOn(BrightnessSensorController.class).findByID(dtoObj.getUid())).withSelfRel());
        return dtoObj;
    }
    
    public BrightnessSensorDTO update(BrightnessSensorDTO objDTO) {
        if(objDTO == null) throw new RequiredObjectIsNullException();
        var entity = repository.findById(objDTO.getUid()).orElseThrow(() -> 
			new ResourceNotFoundException("There are no records for this id! UID: " + objDTO.getUid()));
        entity.setLux(objDTO.getLux());
        entity.setTimesStampUTC(objDTO.getTimesStampUTC());
        BrightnessSensorDTO  dtoObj = mapper.map(repository.save(entity), BrightnessSensorDTO.class);
        dtoObj.add(linkTo(methodOn(BrightnessSensorController.class).findByID(dtoObj.getUid())).withSelfRel());
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
