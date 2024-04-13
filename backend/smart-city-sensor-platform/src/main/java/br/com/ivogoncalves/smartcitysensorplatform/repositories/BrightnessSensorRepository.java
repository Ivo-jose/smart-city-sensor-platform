package br.com.ivogoncalves.smartcitysensorplatform.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.ivogoncalves.smartcitysensorplatform.models.BrightnessSensor;

@Repository
public interface BrightnessSensorRepository extends MongoRepository<BrightnessSensor,Long> {}
