package br.com.ivogoncalves.smartcitysensorplatform.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivogoncalves.smartcitysensorplatform.models.dtos.BrightnessSensorDTO;
import br.com.ivogoncalves.smartcitysensorplatform.services.BrightnessSensorService;
import br.com.ivogoncalves.smartcitysensorplatform.utils.MediaType;

@RestController
@RequestMapping(value ="/api/brightness/v1")
public class BrightnessSensorController {

    @Autowired
    private BrightnessSensorService service;
    
    @GetMapping(value = "/{uid}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BrightnessSensorDTO findByID(@PathVariable(value = "uid") String uid) {
    	return service.findById(uid);
    }
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<BrightnessSensorDTO> findAll() {
    	return service.findAll();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, 
    			 consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BrightnessSensorDTO create(@RequestBody BrightnessSensorDTO objDTO){
        return service.create(objDTO);
    }
    
    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, 
    		    consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public BrightnessSensorDTO update(@RequestBody BrightnessSensorDTO objDTO) {
    	return service.update(objDTO);
    }
    
    @DeleteMapping(value = "/{uid}")
    public ResponseEntity<?> delete(@PathVariable(value = "uid") String uid) {
    	service.delete(uid);
    	return ResponseEntity.noContent().build();
    }
    
}
