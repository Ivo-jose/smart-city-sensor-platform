package br.com.ivogoncalves.smartcitysensorplatform.services;

import br.com.ivogoncalves.smartcitysensorplatform.exceptions.RequiredObjectIsNullException;
import br.com.ivogoncalves.smartcitysensorplatform.models.BrightnessSensor;
import br.com.ivogoncalves.smartcitysensorplatform.models.dto.BrightnessSensorDTO;
import br.com.ivogoncalves.smartcitysensorplatform.repositories.BrightnessSensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BrightnessSensorService {

    @Autowired
    private BrightnessSensorRepository repository;
    @Autowired
    private ModelMapper mapper;
    private Logger logger = Logger.getLogger(BrightnessSensorService.class.getName());

    public BrightnessSensorDTO create(BrightnessSensorDTO objDTO) {
        if(objDTO == null) throw new RequiredObjectIsNullException();
        logger.info("Create a new brightness sensor");
        var obj = mapper.map(objDTO, BrightnessSensor.class);
        return mapper.map(repository.save(obj), BrightnessSensorDTO.class);

    }
}
